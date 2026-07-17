package org.example

fun main() {
    println("=== Problem 1 (Priority Dispatcher) ===")
    println("Description: Loops through package indices from 1 to 50 and classifies them based on multiples of 3 and 5.")
    for (index in 1..50) {
        val isMultipleOfThree = (index % 3 == 0)
        val isMultipleOfFive = (index % 5 == 0)

        if (isMultipleOfThree && isMultipleOfFive) {
            println("Index $index: PriorityExpress")
        } else if (isMultipleOfThree) {
            println("Index $index: Express")
        } else if (isMultipleOfFive) {
            println("Index $index: Freight")
        } else {
            println("Index $index: $index")
        }
    }
    println("************************************************************************\n")
    //*-**-*-----------------------------------------------



    println("=== Problem 2 (Waypoints Reverser) ===")
    println("Description: Manually reverses a text-based route transaction string.")

    val inputTextTransaction = "HubA -> HubB -> HubC"
    println("Original Route: $inputTextTransaction")

    // Splitting the text by the arrow delimiter with spaces
    val updateInputTransaction = inputTextTransaction.split(" -> ")
    val lengthInput = updateInputTransaction.size - 1
    var reversOutput = ""

    // Looping backwards from the last element to index 0
    for (index in 0..lengthInput) {
        val goalsOfText = lengthInput - index
        val currentChar = updateInputTransaction[goalsOfText]

        if (reversOutput.isEmpty()) {
            reversOutput = currentChar
        } else {
            reversOutput = reversOutput + " -> " + currentChar
        }
    }

    println("Reversed Route output: $reversOutput")
    println("************************************************************************\n")
//----------------------------------------------------***-*------------------------------------------

    println("=== Problem 3 (Max Weight Filter) ===")
    println("Description: Checks and filters aid parcel weights based on a valid threshold.")

    val weightOfAidParcels = doubleArrayOf(45.00, -2.00, 18.5, 20.5, 17.4, -20.8, -10.8)
    val invalidWeight = -0.1
    println("Analyzing parcel weights:")

    for (weight in weightOfAidParcels) {
        if (weight > invalidWeight) {
            println("-> Weight ($weight): Acceptable for use.")
        } else {
            println("-> Weight ($weight): NOT acceptable for use.")
        }
    }
    println("************************************************************************\n")
//-----------------------------------------------------------------------------------------------

    println("=== Problem 4 (Palindrome Transit Code) ===")
    println("Description: Case-insensitive double-pointer verification to check if a transit code is a palindrome.")

    val transitCode = "TR808RT"
    val caseInsensitive = transitCode.lowercase()
    var startCode = 0
    var endCode = caseInsensitive.length - 1
    var isPalindrome = true

    // Compare characters from both ends moving inwards
    while (startCode < endCode) {
        if (caseInsensitive[startCode] != caseInsensitive[endCode]) {
            isPalindrome = false
            break
        }
        startCode++
        endCode--
    }

    if (isPalindrome) {
        println("The code '$transitCode' IS a valid Palindrome.")
    } else {
        println("The code '$transitCode' is NOT a Palindrome.")
    }
    println("************************************************************************\n")


    //---------------------------------------------------------------------------------------------
    println("=== Problem 5 (Binary Search Lookup) ===")
    println("Description: Optimized manual binary search algorithm to locate a target ID within a sorted array ")

    val number = intArrayOf(1, 5, 7, 8, 11, 14, 85)
    val targetOfArray = 11
    var foundIndex = -1

    var startArray = 0
    var endArray = number.size - 1

    while (startArray <= endArray) {
        val midArray = startArray + (endArray - startArray) / 2

        if (number[midArray] == targetOfArray) {
            foundIndex = midArray
            break
        } else if (number[midArray] < targetOfArray) {
            startArray = midArray + 1 // Shift search boundaries to the right half
        } else {
            endArray = midArray - 1   // Shift search boundaries to the left half
        }
    }

    if (foundIndex != -1) {
        println("Target : $targetOfArray is Found at Index: $foundIndex")
    } else {
        println("Target : $targetOfArray is Not Found")
    }
}