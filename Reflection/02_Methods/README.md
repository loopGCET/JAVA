# Methods Reflection

Inspect and invoke methods dynamically using reflection.

## Getting Methods

```java
Method[] methods = clazz.getDeclaredMethods();
Method method = clazz.getMethod("methodName", parameterTypes);
```

## Invoking Methods

```java
Method method = clazz.getMethod("getName");
Object result = method.invoke(instance);
```

For details, see: **[MethodsExample.java](MethodsExample.java)**

