# Java File I/O & NIO

> Complete guide to file handling and I/O operations in Java

[![Java](https://img.shields.io/badge/Java-8%2B-blue.svg)](https://www.oracle.com/java/)
[![Examples](https://img.shields.io/badge/Examples-7-brightgreen.svg)]()

## 📋 Overview

Java provides comprehensive APIs for file and I/O operations, from basic byte/character streams to modern NIO.2 Path API.

## 🗂️ Topics

1. **Byte Streams** - Binary I/O with InputStream/OutputStream
2. **Character Streams** - Text I/O with Reader/Writer
3. **Buffered I/O** - Efficient I/O with buffering
4. **File Class** - Legacy file operations
5. **Paths & Files** - Modern NIO.2 API
6. **NIO Channels** - Non-blocking I/O
7. **Serialization** - Object persistence

## 🚀 Quick Example

```java
// Reading a file (modern way)
List<String> lines = Files.readAllLines(Paths.get("file.txt"));

// Writing to a file
Files.write(Paths.get("output.txt"), 
    "Hello, World!".getBytes());
```

---

**Status:** ✅ Complete

