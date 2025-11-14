# Functional Interfaces

A functional interface has exactly one abstract method and can be used with lambda expressions.

## @FunctionalInterface Annotation

```java
@FunctionalInterface
public interface MyFunction {
    void execute();
    // Only one abstract method allowed
}
```

## Built-in Functional Interfaces

- **Predicate<T>** - boolean test(T t)
- **Function<T,R>** - R apply(T t)
- **Consumer<T>** - void accept(T t)
- **Supplier<T>** - T get()

For details, see: **[FunctionalInterfacesExample.java](FunctionalInterfacesExample.java)**

