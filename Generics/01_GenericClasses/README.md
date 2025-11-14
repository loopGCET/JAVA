# Generic Classes

Generic classes allow you to parameterize types.

## Syntax
```java
public class Box<T> {
    private T value;
    
    public void set(T value) { this.value = value; }
    public T get() { return value; }
}

// Usage
Box<String> stringBox = new Box<>();
Box<Integer> intBox = new Box<>();
```

For details, see: **[GenericClassesExample.java](GenericClassesExample.java)**

