# Java Reflection API

> Runtime class inspection and manipulation

[![Java](https://img.shields.io/badge/Java-1.1%2B-blue.svg)](https://www.oracle.com/java/)

## Overview

Reflection allows inspection of classes, interfaces, fields and methods at runtime.

## Topics

1. Class Object - Getting and using Class objects
2. Methods - Invoking methods dynamically
3. Fields - Accessing and modifying fields
4. Constructors - Creating instances dynamically
5. Dynamic Proxy - Creating proxies at runtime

## Example

```java
Class<?> clazz = MyClass.class;
Method method = clazz.getMethod("methodName");
Object result = method.invoke(instance);
```

**Status:** ✅ Complete

