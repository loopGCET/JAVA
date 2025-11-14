# Generic Methods

Methods can have their own type parameters.

```java
public static <T> void printArray(T[] array) {
    for (T element : array) {
        System.out.println(element);
    }
}
```

For details, see: **[GenericMethodsExample.java](GenericMethodsExample.java)**

