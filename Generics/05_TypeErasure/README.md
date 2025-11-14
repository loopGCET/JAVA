# Type Erasure

Java generics are implemented using type erasure - generic type information is removed at compile time.

```java
List<String> list = new ArrayList<>();
// At runtime, becomes:
List list = new ArrayList();
```

This ensures backward compatibility but has limitations.

For details, see: **[TypeErasureExample.java](TypeErasureExample.java)**

