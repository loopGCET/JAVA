# Custom Annotations

Create your own annotations for specific use cases.

## Creating Custom Annotations

```java
@interface MyAnnotation {
    String value();
    int count() default 1;
}

@MyAnnotation(value = "Example", count = 5)
public class MyClass {
    // ...
}
```

## Annotation Elements
- Can have elements (like methods)
- Can have default values
- Must be public or package-private

For details, see: **[CustomAnnotationsExample.java](CustomAnnotationsExample.java)**

