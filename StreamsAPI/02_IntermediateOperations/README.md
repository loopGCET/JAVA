# Intermediate Operations

Intermediate operations return a new stream and are lazy (not executed until terminal operation).

## Key Operations

- **filter** - Select elements matching predicate
- **map** - Transform each element
- **flatMap** - Flatten nested structures
- **distinct** - Remove duplicates
- **sorted** - Sort elements
- **limit** - Take first n elements
- **skip** - Skip first n elements
- **peek** - Debug/side effects

## Examples

```java
stream.filter(n -> n > 10)
stream.map(String::toUpperCase)
stream.flatMap(list -> list.stream())
stream.distinct()
stream.sorted()
stream.limit(5)
```

For details, see: **[IntermediateOperationsExample.java](IntermediateOperationsExample.java)**

