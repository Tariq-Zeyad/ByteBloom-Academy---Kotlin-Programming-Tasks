package org.example

/**
 * Custom exception for structural mismatches.
 *  Output a descriptive custom domain exception.
 */
class StructuralMismatchException(message: String) : Exception(message)
/**
 *  Extracts letters, numbers, and structural symbols.
 *    Removes all forms of whitespace.
 */
fun extractEssentialCharacters(rawCargo: String): String {
    val builder = StringBuilder()
    for (i in rawCargo.indices) {
        val char = rawCargo[i]
        if (char != ' ' && char != '\t' && char != '\n' && char != '\r') {
            builder.append(char)
        }
    }
    return builder.toString()
}
/**
 *  Filters structural noise by removing redundant commas.
 */
fun filterConsecutiveCommas(compactCargo: String): String {
    val builder = StringBuilder()
    var  wasPreviousCharComma = false

    for (i in compactCargo.indices) {
        val currentChar = compactCargo[i]

        if (currentChar == ',') {
            if (wasPreviousCharComma) continue // Skip this duplicate comma
            wasPreviousCharComma = true
        } else {
            wasPreviousCharComma = false
        }

        builder.append(currentChar)
    }
    return builder.toString()
}

/**
 *  Sanitizes the raw input string
 */
fun sanitizeCargoString(rawCargo: String): String {
    val essentialOnly = extractEssentialCharacters(rawCargo)
    return filterConsecutiveCommas(essentialOnly)
}

/**
 *  Validates bracket structural balance.
 *    Throws Custom Domain Exception if unbalanced.
 */
fun validateBracketBalance(sanitizedCargo: String) {
    var balance = 0
    for (char in sanitizedCargo) {
        when (char) {
            '[' -> balance++
            ']' -> balance--
        }
    }

    if (balance != 0) {
        throw StructuralMismatchException(
            "Structural Mismatch: Unbalanced brackets detected. Missing [ or ]"
        )
    }
}

/**
 *  Checks if a captured token is a pure package ID starting with "PKG-".
 */
fun isValidPackageId(candidateToken: String): Boolean = candidateToken.startsWith("PKG-")
/**
 * ===================================================================================
 * JVM CALL STACK & FRAME STRUCTURE TRACE DOCUMENTATION
 * ===================================================================================
 *
 * Every time this recursive function runs, the JVM allocates a "Stack Frame" on the Call Stack.
 *
 * Inside this Frame, memory is divided into 4 main parts:
 *
 * 1. Parameters:
 *    - Holds function arguments: sanitizedCargo (reference) and startPosition (primitive int).
 *
 * 2. Local Variables Table:
 *    - Stores variables created inside this frame: extractedIds (list reference),
 *      currentToken (StringBuilder reference), and currentPos (primitive int).
 *
 * 3. Operand Stack:
 *    - The JVM's temporary workspace used to evaluate conditions, build strings (via .append),
 *      and prepare arguments before calling other methods.
 *
 * 4. Frame Data (Return Address):
 *    - Holds the exact instruction pointer so the JVM knows where to resume execution
 *      in the calling function after this frame finishes.
 *
 * -----------------------------------------------------------------------------------
 * FLOW TRACE (Creation & Destruction of Stack Frames):
 * -----------------------------------------------------------------------------------
 * - Calling [parseCargoRecursive] pushes a new Frame onto the Call Stack.
 *
 * - Finding '[' recursively calls the function again, pushing a new child Frame onto the stack
 *   (the parent Frame is suspended and waits for the child result).
 *
 * - Finding ']' ends the current recursive level, returns the CargoParseResult to the parent,
 *   and pops the active Frame from the stack. Control returns to the parent's Return Address.
 *
 * - Finding ',' acts as a delimiter, finalizing the current token without altering the stack.
 *
 * - Structural Validation Note:
 *   Unbalanced brackets are detected and prevented BEFORE reaching this recursion logic
 *   by the [validateBracketBalance] function. This guarantees that an OutOfBounds crash
 *   is avoided, and a descriptive custom domain exception is thrown instead (Requirement #5).
 * ===================================================================================
 */
data class CargoParseResult(
    val packageIds: List<String>,
    val nextPosition: Int
)

fun parseCargoRecursive(
    sanitizedCargo: String,
    startPosition: Int
): CargoParseResult {

    val extractedIds = mutableListOf<String>()
    var currentToken = StringBuilder()
    var currentPos = startPosition

    while (currentPos < sanitizedCargo.length) {
        val char = sanitizedCargo[currentPos]

        when (char) {
            '[' -> {
                // Start new Recursion (Push Stack Frame)
                val nestedResult = parseCargoRecursive(sanitizedCargo, currentPos + 1)

                extractedIds.addAll(nestedResult.packageIds)
                currentPos = nestedResult.nextPosition
                currentToken = StringBuilder() // Reset token after nested block
            }

            ']', ',' -> {
                // Finalize current token upon hitting a delimiter
                if (isValidPackageId(currentToken.toString())) {
                    extractedIds.add(currentToken.toString())
                }
                currentToken = StringBuilder()

                // ']'(Pop Stack Frame)
                if (char == ']') {
                    return CargoParseResult(extractedIds, currentPos)
                }
            }

            else -> {
                // Accumulate characters (structural noise or IDs)
                currentToken.append(char)
            }
        }
        currentPos++
    }

    // Handles dangling tokens at the end of the string
    if (isValidPackageId(currentToken.toString())) {
        extractedIds.add(currentToken.toString())
    }

    return CargoParseResult(extractedIds, currentPos)
}

/**
 * 7. Main entry point for the parser.
 *    Orchestrates Sanitization -> Bracket Validation -> Recursive Parsing.
 */
fun parseContainer(rawCargoExpression: String): List<String> {
    val sanitizedExpression = sanitizeCargoString(rawCargoExpression)

    validateBracketBalance(sanitizedExpression)

    val (finalPackageIds, _) = parseCargoRecursive(sanitizedExpression, 0)
    return finalPackageIds
}

// =============================================================
// TEST RUNNER
// =============================================================
fun main() {
    println("=== Container Parser Tests ===")

    val testCases = mapOf(
        "Spaces & Noise" to " Crate [ Box [ PKG-101 ] , PKG-102 ] ",
        "Empty Brackets" to "Crate[Box[],PKG-202]",
        "Consecutive Commas" to "Box[PKG-101,,PKG-102]",
        "Structural Mismatch" to "Crate[PKG-100"
    )

    for ((testName, input) in testCases) {
        print("Testing '$testName': ")
        try {
            val result = parseContainer(input)
            println("SUCCESS -> $result")
        } catch (e: StructuralMismatchException) {
            println("FAILED (Expected Exception) -> ${e.message}")
        } catch (e: Exception) {
            println("CRASHED (Unexpected Exception) -> ${e.message}")
        }
    }
}