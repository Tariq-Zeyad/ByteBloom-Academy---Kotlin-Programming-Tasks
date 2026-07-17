<div align="center">


### Advanced Kotlin Problem Solving Collection

<p>
A collection of Kotlin implementations covering algorithmic thinking,
data processing, searching, recursion, and parsing techniques.
</p>

</div>

<div align="center">

![Kotlin](https://img.shields.io/badge/Kotlin-1.8%2B-purple?style=for-the-badge&logo=kotlin)
![JVM](https://img.shields.io/badge/JVM-17-blue?style=for-the-badge&logo=openjdk)
![Algorithm](https://img.shields.io/badge/Algorithms-Practice-green?style=for-the-badge)

</div>

# 📌 Project Overview

This repository contains two major Kotlin tasks designed to demonstrate practical programming concepts.

<table>
<tr>
<th>Task</th>
<th>Main Concepts</th>
</tr>

<tr>
<td>

## Task 1

Basic Algorithm Problems

</td>

<td>

- Conditions
- Loops
- Arrays
- Strings
- Searching Algorithms
- Two Pointer Technique

</td>
</tr>


<tr>
<td>

## Task 2

Recursive Cargo Parser

</td>

<td>

- Recursion
- Parsing
- Validation
- Custom Exceptions
- JVM Stack Frames

</td>
</tr>

</table>

---

# 🟦 Task 1 - Basic Algorithm Problems

## Overview

Task 1 contains five independent Kotlin problems focusing on fundamental algorithm implementation.

---

# 🚦 Problem 1 - Priority Dispatcher

## Description

The program classifies package indexes from:

```
1 → 50
```

based on divisibility rules.

<table>

<tr>
<th>Condition</th>
<th>Result</th>
</tr>

<tr>
<td>Multiple of 3 and 5</td>
<td>PriorityExpress</td>
</tr>

<tr>
<td>Multiple of 3</td>
<td>Express</td>
</tr>

<tr>
<td>Multiple of 5</td>
<td>Freight</td>
</tr>

<tr>
<td>Otherwise</td>
<td>Index Number</td>
</tr>

</table>

### Example

```
Index 3: Express

Index 5: Freight

Index 15: PriorityExpress
```

### Complexity

```
Time Complexity: O(n)

Space Complexity: O(1)
```

---

# 🔄 Problem 2 - Waypoints Reverser

## Description

Reverses a route transaction string.

Example:

Input:

```
HubA -> HubB -> HubC
```

Output:

```
HubC -> HubB -> HubA
```

## Implementation Steps

```
Split Route
      |
      ↓
Traverse Backwards
      |
      ↓
Build New Route
```

### Complexity

```
Time: O(n)

Space: O(n)
```

---

# ⚖️ Problem 3 - Max Weight Filter

## Description

Checks aid parcel weights against a validation threshold.

Rule:

```
weight > -0.1
```

Example:

```
45.0  → Acceptable

-2.0 → NOT acceptable
```

### Complexity

```
Time: O(n)

Space: O(1)
```

---

# 🔁 Problem 4 - Palindrome Transit Code

## Description

Checks if a transit code reads the same forward and backward.

The solution uses a two-pointer approach.

Example:

```
TR808RT
```

Comparison:

```
T ↔ T

R ↔ R

8 ↔ 8

0 ↔ 0
```

Result:

```
Valid Palindrome
```

### Complexity

```
Time: O(n)

Space: O(1)
```

---

# 🔍 Problem 5 - Binary Search Lookup

## Description

Searches for a target ID inside a sorted array.

Example:

Array:

```
[1,5,7,8,11,14,85]
```

Target:

```
11
```

Output:

```
Found at Index 4
```

## Algorithm

```
Find Middle Element
        |
        ↓
Compare Target
        |
        ↓
Discard Half
        |
        ↓
Repeat
```

### Complexity

```
Time: O(log n)

Space: O(1)
```

---

<br>

# 🟩 Task 2 - Recursive Container Cargo Parser

## Overview

Task 2 introduces a recursive parser capable of extracting valid package IDs from nested cargo structures.

The parser handles:

<ul>
<li>Input cleaning</li>
<li>Structural validation</li>
<li>Recursive traversal</li>
<li>Package extraction</li>
<li>Error handling</li>
</ul>

---

# 🧹 Input Sanitization

The parser removes:

<ul>
<li>Spaces</li>
<li>Tabs</li>
<li>New lines</li>
<li>Carriage returns</li>
</ul>


Example:

Input:

```
Crate [ Box [ PKG-101 ] ]
```

Output:

```
Crate[Box[PKG-101]]
```

---

# 🧱 Structural Validation

Before recursion starts, the parser validates brackets.

Valid:

```
Crate[PKG-100]
```

Invalid:

```
Crate[PKG-100
```

Invalid structures throw:

```kotlin
StructuralMismatchException
```

---

# 🌳 Recursive Parsing

Main function:

```kotlin
parseCargoRecursive()
```

Example:

Input:

```
Crate[Box[PKG-101],PKG-102]
```

Output:

```
[
 PKG-101,
 PKG-102
]
```

---

# 🧠 JVM Stack Frame Behavior

Each recursive call creates a new JVM stack frame.

Each frame contains:

<table>

<tr>
<th>Component</th>
<th>Purpose</th>
</tr>

<tr>
<td>Parameters</td>
<td>Function arguments</td>
</tr>

<tr>
<td>Local Variables</td>
<td>Temporary values</td>
</tr>

<tr>
<td>Operand Stack</td>
<td>Intermediate operations</td>
</tr>

<tr>
<td>Return Address</td>
<td>Resume location</td>
</tr>

</table>

---

# 📦 Package ID Validation

Only IDs starting with:

```
PKG-
```

are accepted.

Examples:

✅ Valid

```
PKG-101
PKG-202
```

❌ Invalid

```
BOX-101
ITEM-1
```

---

# 📊 Complexity Analysis

<table>

<tr>
<th>Operation</th>
<th>Time</th>
<th>Space</th>
</tr>

<tr>
<td>Sanitization</td>
<td>O(n)</td>
<td>O(n)</td>
</tr>

<tr>
<td>Validation</td>
<td>O(n)</td>
<td>O(1)</td>
</tr>

<tr>
<td>Recursive Parsing</td>
<td>O(n)</td>
<td>O(d)</td>
</tr>

</table>

Where:

```
d = maximum nesting depth
```

---

# 📂 Project Structure

```
src
└── main
    └── kotlin
        └── TrackerGauntlet.kt
        └── ContainerParser.kt

```

---

# ▶️ Running The Project

## Requirements

<table>

<tr>
<td>Kotlin</td>
<td>1.8+</td>
</tr>

<tr>
<td>JVM</td>
<td>21+</td>
</tr>

</table>


## IntelliJ IDEA

1. Open project.
2. Enable Kotlin support.
3. Run:

```
Main.kt
```

---

## Command Line

Compile:

```bash
kotlinc Main.kt -include-runtime -d application.jar
```

Run:

```bash
java -jar application.jar
```

---

<div align="center">

## 🚀 Final Summary

This repository demonstrates Kotlin programming through:

<table>
<tr>
<td align="center">🧩 Algorithms</td>
<td align="center">🌳 Recursion</td>
<td align="center">🔍 Searching</td>
<td align="center">⚙️ Parsing</td>
</tr>
</table>

Built with Kotlin and focused on clean,
efficient, and maintainable solutions.

</div>
