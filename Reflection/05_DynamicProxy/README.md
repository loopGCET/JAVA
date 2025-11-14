# Dynamic Proxy

Create proxy instances dynamically at runtime.

## Creating Proxy

```java
Interface proxy = (Interface) Proxy.newProxyInstance(
    classLoader,
    new Class<?>[] { Interface.class },
    invocationHandler
);
```

## InvocationHandler

```java
InvocationHandler handler = (proxy, method, args) -> {
    // Handle method invocation
    return result;
};
```

For details, see: **[DynamicProxyExample.java](DynamicProxyExample.java)**

