# Java Generics

> Type-safe programming with parameterized types

[![Java](https://img.shields.io/badge/Java-5%2B-blue.svg)](https://www.oracle.com/java/)
[![Examples](https://img.shields.io/badge/Examples-5-brightgreen.svg)]()

## 📋 Overview

Generics enable types (classes and interfaces) to be parameters when defining classes, interfaces, and methods. They provide compile-time type safety.

## 🗂️ Topics

1. **Generic Classes** - Creating parameterized classes
2. **Generic Methods** - Type-safe methods
3. **Bounded Types** - Restricting type parameters
4. **Wildcards** - Flexible type bounds
5. **Type Erasure** - How generics work internally

## 🚀 Quick Example

```java
// Without Generics
List list = new ArrayList();
list.add("Hello");
String s = (String) list.get(0); // Cast needed

// With Generics
List<String> list = new ArrayList<>();
list.add("Hello");
String s = list.get(0); // No cast needed
```

---

**Status:** ✅ Complete

