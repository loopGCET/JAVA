# Built-in Annotations

Java provides several built-in annotations for common use cases.

## Common Built-in Annotations

- **@Override** - Indicates method overrides superclass method
- **@Deprecated** - Marks element as deprecated
- **@SuppressWarnings** - Suppresses compiler warnings
- **@FunctionalInterface** - Marks interface as functional
- **@SafeVarargs** - Suppresses varargs warnings

## Examples

```java
@Override
public String toString() {
    return "Example";
}

@Deprecated
public void oldMethod() {
    // This method is deprecated
}

@SuppressWarnings("unchecked")
public void method() {
    // Suppress unchecked warnings
}
```

For details, see: **[BuiltInAnnotationsExample.java](BuiltInAnnotationsExample.java)**

