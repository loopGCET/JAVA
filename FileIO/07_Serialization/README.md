# Serialization

Convert objects to byte stream for storage or transmission.

```java
// Serialize
try (ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream("object.ser"))) {
    oos.writeObject(myObject);
}

// Deserialize
try (ObjectInputStream ois = new ObjectInputStream(
        new FileInputStream("object.ser"))) {
    MyClass obj = (MyClass) ois.readObject();
}
```

For details, see: **[SerializationExample.java](SerializationExample.java)**

