# Predicate

Predicate<T> represents a boolean-valued function of one argument.

```java
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
```

## Usage
```java
Predicate<Integer> isEven = n -> n % 2 == 0;
isEven.test(4); // true
```

For details, see: **[PredicateExample.java](PredicateExample.java)**

