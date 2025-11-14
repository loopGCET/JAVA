# Class Object

The Class object represents classes and interfaces in a running Java application.

## Getting Class Objects

```java
// Three ways to get Class object
Class<?> clazz1 = MyClass.class;
Class<?> clazz2 = obj.getClass();
Class<?> clazz3 = Class.forName("com.example.MyClass");
```

## Class Information

```java
String name = clazz.getName();
Class<?> superclass = clazz.getSuperclass();
Class<?>[] interfaces = clazz.getInterfaces();
int modifiers = clazz.getModifiers();
```

For details, see: **[ClassObjectExample.java](ClassObjectExample.java)**

