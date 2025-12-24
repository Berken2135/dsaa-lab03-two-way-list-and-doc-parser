# dsaa-lab03-two-way-list-and-doc-parser

This repository contains the solution for **DSAA Lab 03**.  
The project is a **command-line Java application** that implements a two-way (doubly) unordered list with head and tail, and parses documents to extract valid link identifiers.

---

## Features

### 1. Two-Way Unordered List (Head & Tail)
- Custom implementation of a **two-way (doubly) linked list**
- Maintains **head and tail**
- Supports iteration and reverse output (where applicable)

### 2. Document Reading & Link Parsing
- Reads a document line by line from **standard input**
- Document reading ends when the line `eod` is entered
- Extracts tokens starting with `link=` and keeps only **valid identifiers**
- Valid identifier rule: starts with a letter, then letters/digits/underscore

---

## Project Structure

```text
dsaa.lab03
├── Main.java
├── Document.java
├── Link.java
└── TwoWayUnorderedListWithHeadAndTail.java
```