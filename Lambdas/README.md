# Java Lambdas & Functional Programming

> Master functional programming in Java with lambda expressions and functional interfaces

[![Java](https://img.shields.io/badge/Java-8%2B-blue.svg)](https://www.oracle.com/java/)
[![Examples](https://img.shields.io/badge/Examples-7-brightgreen.svg)]()

## 📋 Overview

Lambda expressions, introduced in Java 8, enable functional programming style with concise syntax for implementing functional interfaces.

## 🗂️ Topics

1. **Lambda Basics** - Syntax and basic usage
2. **Functional Interfaces** - @FunctionalInterface annotation
3. **Method References** - Concise lambda alternatives
4. **Predicate** - Boolean-valued functions
5. **Function** - Transform input to output
6. **Consumer** - Consume values without return
7. **Supplier** - Supply values without input

## 🚀 Quick Example

```java
// Old way
List<String> names = Arrays.asList("Alice", "Bob");
for (String name : names) {
    System.out.println(name);
}

// Lambda way
names.forEach(name -> System.out.println(name));
names.forEach(System.out::println); // Method reference
```

---

**Related Modules:** [Streams API](../StreamsAPI/) | [Collections](../CollectionFramework/)

**Status:** ✅ Complete

