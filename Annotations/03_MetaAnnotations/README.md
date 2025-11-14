# Meta-Annotations

Annotations that apply to other annotations.

## Key Meta-Annotations

- **@Retention** - How long annotation is retained
- **@Target** - Where annotation can be applied
- **@Documented** - Include in JavaDoc
- **@Inherited** - Inherited by subclasses

## Retention Policies

```java
@Retention(RetentionPolicy.SOURCE)   // Discarded by compiler
@Retention(RetentionPolicy.CLASS)    // In .class file, not at runtime
@Retention(RetentionPolicy.RUNTIME)  // Available at runtime
```

For details, see: **[MetaAnnotationsExample.java](MetaAnnotationsExample.java)**

