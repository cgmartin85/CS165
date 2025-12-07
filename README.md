# CS165 Class Projects

This repository contains a collection of class projects for the CS165 course (Computer Science 2: Data Structures and Algorithms). The projects are implemented in Java and cover various data structures and algorithms concepts.

## Projects Overview

The repository is organized into several directories, each corresponding to a specific project or assignment:

### P7: Expression Tree

This project involves implementing an expression tree to parse, convert, build, traverse, and evaluate mathematical expressions.

*   **Key Files:**
    *   `ATree.java`: Abstract base class for the expression tree.
    *   `ExpressionTree.java`: Concrete implementation of the expression tree logic.
    *   `TestCode.java`: Test harness for the expression tree.

### P8: Expression Tree Expanded

An extension of the P7 project that adds support for variables and a symbol table. It allows evaluating expressions involving variables.

*   **Key Files:**
    *   `SymbolTable.java`: Implements a symbol table using a binary search tree to store variable names and values.
    *   `ExpressionTreeExpanded.java`: Extends `ExpressionTree` to handle variables using the symbol table.
    *   `Shell.java`: A command-line shell for interacting with the expression tree and symbol table.

### P9: Hashing

This project implements a hash table using chaining for collision resolution. It includes various hash functions and performance analysis.

*   **Key Files:**
    *   `HashTable.java`: Implementation of the hash table.
    *   `Hasher.java`: Defines different hash functions (FIRST, SUM, PRIME, JAVA).
    *   `TestCode.java`: A shell-based test harness for the hash table.

### P10: Graph Algorithms

This project focuses on graph algorithms, specifically building a graph from a mileage chart, performing depth-first and breadth-first searches, and calculating shortest paths using Dijkstra's algorithm.

*   **Key Files:**
    *   `GraphAbstract.java`: Abstract base class for the graph.
    *   `GraphImplementation.java`: Concrete implementation of graph operations (BFS, DFS, Dijkstra).

## Prerequisites

*   Java Development Kit (JDK) 8 or higher.

## How to Compile and Run

Each project is self-contained in its own directory. To compile and run a project, navigate to the project directory and use the standard Java compiler and runtime.

### Example: Running P7

```bash
cd P7
javac -d . src/*.java
java TestCode "3 + 4 * 5"
```

### Example: Running P8

```bash
cd P8
javac -d . src/*.java
java Shell
# Then enter commands like:
# x = 10
# y = 5
# x + y
```

### Example: Running P9

```bash
cd P9
javac -d . src/*.java
java TestCode
# Then enter commands like:
# table 10
# hasher JAVA
# insert hello
# print
```

### Example: Running P10

```bash
cd P10
javac -d . src/*.java
java GraphImplementation <distanceFile> <graphFile>
# You will need a distance file (CSV) as input.
```

## Documentation

The source code is thoroughly documented with Javadoc. You can generate the HTML documentation using the `javadoc` tool.

```bash
javadoc -d docs src/*.java
```

## Author

Projects originally authored by Chris Wilcox, Fritz Sieker, and Christopher Martin for CS165 at Colorado State University.
