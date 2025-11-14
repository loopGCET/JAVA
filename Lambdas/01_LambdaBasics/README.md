# Lambda Basics

Lambda expressions provide a concise way to implement functional interfaces.

## Syntax

```java
// No parameters
() -> System.out.println("Hello")

// Single parameter
x -> x * x

// Multiple parameters
(x, y) -> x + y

// With block
(x, y) -> {
    int sum = x + y;
    return sum;
}
```

## Examples

```java
// Old way
Runnable r1 = new Runnable() {
    public void run() {
        System.out.println("Hello");
    }
};

// Lambda way
Runnable r2 = () -> System.out.println("Hello");
```

For details, see: **[LambdaBasicsExample.java](LambdaBasicsExample.java)**

