# Annotation Processing

Process annotations at compile-time or runtime.

## Runtime Processing

```java
if (obj.getClass().isAnnotationPresent(MyAnnotation.class)) {
    MyAnnotation annotation = obj.getClass().getAnnotation(MyAnnotation.class);
    // Process annotation
}
```

## Compile-Time Processing

Use annotation processors to generate code or validate at compile time.

For details, see: **[AnnotationProcessingExample.java](AnnotationProcessingExample.java)**

