# Streams API - Testing Guide

## Prerequisites

- JDK 8 or higher (Streams introduced in Java 8)
- Command line or IDE

## Running Examples

### Individual Topic
```bash
cd 01_StreamBasics
javac StreamBasicsExample.java
java StreamBasicsExample
```

### All Topics
```bash
# Compile and run each
for dir in 0*/; do
    cd "$dir"
    javac *.java && java *Example
    cd ..
done
```

## Expected Behavior

All examples should:
- ✅ Compile without errors
- ✅ Run successfully
- ✅ Display clear output
- ✅ Demonstrate stream operations

---

**Related:** [README](README.md) | [INDEX](INDEX.md)

