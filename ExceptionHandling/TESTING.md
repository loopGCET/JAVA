# Java Exception Handling - Testing Guide

> Instructions for compiling, running, and testing all exception handling examples

[![Java](https://img.shields.io/badge/Java-8%2B-blue.svg)](https://www.oracle.com/java/)
[![Tests](https://img.shields.io/badge/Tests-Pass-success.svg)]()

---

## 📋 Table of Contents

- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
- [Testing Individual Examples](#testing-individual-examples)
- [Running All Tests](#running-all-tests)
- [Expected Output](#expected-output)
- [Troubleshooting](#troubleshooting)
- [Verification Checklist](#verification-checklist)

---

## 🔧 Prerequisites

### Required Software
- **JDK 8 or higher** - Java Development Kit
- **Command Line Tool** - Terminal, Command Prompt, or PowerShell
- **Text Editor** (Optional) - For viewing/modifying code

### Verify Java Installation
```bash
# Check Java version
java -version

# Check Java compiler
javac -version
```

**Expected Output:**
```
java version "1.8.0" or higher
javac version "1.8.0" or higher
```

### Environment Setup
- Ensure `java` and `javac` are in your PATH
- Navigate to the ExceptionHandling directory

---

## 🚀 Quick Start

### One Command Test (Windows PowerShell)
```powershell
cd ExceptionHandling

# Test all examples
Get-ChildItem -Directory -Filter "0*" | ForEach-Object {
    Write-Host "`n=== Testing $($_.Name) ===" -ForegroundColor Green
    cd $_.Name
    Get-ChildItem -Filter "*.java" | ForEach-Object {
        Write-Host "Compiling $($_.Name)..." -ForegroundColor Yellow
        javac $_.Name
        if ($LASTEXITCODE -eq 0) {
            $className = $_.BaseName
            Write-Host "Running $className..." -ForegroundColor Cyan
            java $className
        }
    }
    cd ..
}
```

### One Command Test (Bash/Git Bash)
```bash
cd ExceptionHandling

# Test all examples
for dir in 0*/; do
    echo -e "\n=== Testing $dir ==="
    cd "$dir"
    for file in *.java; do
        echo "Compiling $file..."
        javac "$file"
        if [ $? -eq 0 ]; then
            classname="${file%.java}"
            echo "Running $classname..."
            java "$classname"
        fi
    done
    cd ..
done
```

---

## 📝 Testing Individual Examples

### Example 1: Try-Catch-Finally

**Location:** `01_TryCatchFinally/`

```bash
# Navigate to folder
cd 01_TryCatchFinally

# Compile
javac TryCatchFinallyExample.java

# Run
java TryCatchFinallyExample

# Return to root
cd ..
```

**Expected Result:**
- ✅ Compilation succeeds (no errors)
- ✅ Program runs completely
- ✅ Shows 8 different examples
- ✅ Demonstrates try-catch-finally behavior
- ✅ No unhandled exceptions

---

### Example 2: Throws Keyword

**Location:** `02_ThrowsKeyword/`

```bash
# Navigate to folder
cd 02_ThrowsKeyword

# Compile
javac ThrowsExample.java

# Run
java ThrowsExample

# Return to root
cd ..
```

**Expected Result:**
- ✅ Compilation succeeds
- ✅ Shows 7 different examples
- ✅ Demonstrates throws keyword usage
- ✅ Shows exception propagation
- ✅ Proper exception handling throughout

---

### Example 3: Custom Exceptions

**Location:** `03_CustomExceptions/`

```bash
# Navigate to folder
cd 03_CustomExceptions

# Compile
javac CustomExceptionExample.java

# Run
java CustomExceptionExample

# Return to root
cd ..
```

**Expected Result:**
- ✅ Compilation succeeds (compiles multiple classes)
- ✅ Shows 6 different examples
- ✅ Demonstrates custom exception classes
- ✅ Shows validation frameworks
- ✅ Proper exception hierarchies

**Note:** This example creates multiple `.class` files (main class + custom exception classes)

---

### Example 4: Exception Chaining

**Location:** `04_ExceptionChaining/`

```bash
# Navigate to folder
cd 04_ExceptionChaining

# Compile
javac ExceptionChainingExample.java

# Run
java ExceptionChainingExample

# Return to root
cd ..
```

**Expected Result:**
- ✅ Compilation succeeds
- ✅ Shows 5 different examples
- ✅ Demonstrates exception chaining
- ✅ Shows root cause analysis
- ✅ Proper stack trace handling

---

### Example 5: Try-With-Resources

**Location:** `05_TryWithResources/`

```bash
# Navigate to folder
cd 05_TryWithResources

# Compile
javac TryWithResourcesExample.java

# Run
java TryWithResourcesExample

# Return to root
cd ..
```

**Expected Result:**
- ✅ Compilation succeeds
- ✅ Shows 7 different examples
- ✅ Demonstrates AutoCloseable interface
- ✅ Shows resource management
- ✅ No resource leaks

---

## 🧪 Running All Tests

### Automated Test Script (PowerShell)

Create a file `test_all.ps1`:

```powershell
Write-Host "=== Java Exception Handling - Test Suite ===" -ForegroundColor Cyan
Write-Host ""

$totalTests = 0
$passedTests = 0
$failedTests = 0

$topics = @(
    "01_TryCatchFinally",
    "02_ThrowsKeyword",
    "03_CustomExceptions",
    "04_ExceptionChaining",
    "05_TryWithResources"
)

foreach ($topic in $topics) {
    Write-Host "Testing: $topic" -ForegroundColor Yellow
    $totalTests++
    
    cd $topic
    $javaFile = Get-ChildItem -Filter "*.java" | Select-Object -First 1
    
    # Compile
    javac $javaFile.Name 2>&1 | Out-Null
    if ($LASTEXITCODE -ne 0) {
        Write-Host "  ❌ Compilation FAILED" -ForegroundColor Red
        $failedTests++
        cd ..
        continue
    }
    Write-Host "  ✅ Compilation passed" -ForegroundColor Green
    
    # Run
    $className = $javaFile.BaseName
    java $className 2>&1 | Out-Null
    if ($LASTEXITCODE -ne 0) {
        Write-Host "  ❌ Execution FAILED" -ForegroundColor Red
        $failedTests++
    } else {
        Write-Host "  ✅ Execution passed" -ForegroundColor Green
        $passedTests++
    }
    
    cd ..
    Write-Host ""
}

Write-Host "=== Test Summary ===" -ForegroundColor Cyan
Write-Host "Total Tests: $totalTests"
Write-Host "Passed: $passedTests" -ForegroundColor Green
Write-Host "Failed: $failedTests" -ForegroundColor Red
Write-Host ""

if ($failedTests -eq 0) {
    Write-Host "✅ All tests passed!" -ForegroundColor Green
} else {
    Write-Host "❌ Some tests failed" -ForegroundColor Red
}
```

**Run the script:**
```powershell
.\test_all.ps1
```

---

### Automated Test Script (Bash)

Create a file `test_all.sh`:

```bash
#!/bin/bash

echo "=== Java Exception Handling - Test Suite ==="
echo ""

total_tests=0
passed_tests=0
failed_tests=0

topics=(
    "01_TryCatchFinally"
    "02_ThrowsKeyword"
    "03_CustomExceptions"
    "04_ExceptionChaining"
    "05_TryWithResources"
)

for topic in "${topics[@]}"; do
    echo -e "\e[33mTesting: $topic\e[0m"
    ((total_tests++))
    
    cd "$topic"
    java_file=$(ls *.java | head -n 1)
    
    # Compile
    if javac "$java_file" 2>/dev/null; then
        echo -e "  \e[32m✅ Compilation passed\e[0m"
    else
        echo -e "  \e[31m❌ Compilation FAILED\e[0m"
        ((failed_tests++))
        cd ..
        continue
    fi
    
    # Run
    classname="${java_file%.java}"
    if java "$classname" >/dev/null 2>&1; then
        echo -e "  \e[32m✅ Execution passed\e[0m"
        ((passed_tests++))
    else
        echo -e "  \e[31m❌ Execution FAILED\e[0m"
        ((failed_tests++))
    fi
    
    cd ..
    echo ""
done

echo "=== Test Summary ==="
echo "Total Tests: $total_tests"
echo -e "Passed: \e[32m$passed_tests\e[0m"
echo -e "Failed: \e[31m$failed_tests\e[0m"
echo ""

if [ $failed_tests -eq 0 ]; then
    echo -e "\e[32m✅ All tests passed!\e[0m"
else
    echo -e "\e[31m❌ Some tests failed\e[0m"
fi
```

**Make executable and run:**
```bash
chmod +x test_all.sh
./test_all.sh
```

---

## 📊 Expected Output

### Successful Test Run Output

```
=== Java Exception Handling - Test Suite ===

Testing: 01_TryCatchFinally
  ✅ Compilation passed
  ✅ Execution passed

Testing: 02_ThrowsKeyword
  ✅ Compilation passed
  ✅ Execution passed

Testing: 03_CustomExceptions
  ✅ Compilation passed
  ✅ Execution passed

Testing: 04_ExceptionChaining
  ✅ Compilation passed
  ✅ Execution passed

Testing: 05_TryWithResources
  ✅ Compilation passed
  ✅ Execution passed

=== Test Summary ===
Total Tests: 5
Passed: 5
Failed: 0

✅ All tests passed!
```

---

## 🐛 Troubleshooting

### Issue: "javac: command not found" or "'javac' is not recognized"

**Cause:** Java is not installed or not in PATH

**Solution:**
1. Install JDK 8 or higher
2. Add Java to PATH:
   - Windows: Add `C:\Program Files\Java\jdk-X.X.X\bin` to PATH
   - Linux/Mac: Add to `.bashrc` or `.zshrc`: `export PATH=$PATH:/path/to/jdk/bin`
3. Restart terminal
4. Verify: `javac -version`

---

### Issue: "Error: Could not find or load main class"

**Cause:** Running from wrong directory or class not compiled

**Solution:**
1. Ensure you're in the correct directory (e.g., `01_TryCatchFinally/`)
2. Compile first: `javac *.java`
3. Run with exact class name: `java TryCatchFinallyExample` (no `.java` or `.class`)

---

### Issue: "Exception in thread 'main'"

**Cause:** This is actually expected! Examples demonstrate exception handling.

**Solution:**
- This is normal for these examples
- Examples catch and handle exceptions properly
- If program completes, test passes

---

### Issue: Compilation warnings

**Cause:** Some examples intentionally show problematic patterns

**Solution:**
- Warnings are acceptable for educational examples
- Focus on whether it compiles and runs
- Examples demonstrate what NOT to do in some cases

---

### Issue: Different output than expected

**Cause:** Different Java versions may produce slightly different messages

**Solution:**
- Focus on whether the example runs without crashing
- Exception messages may vary by JDK version
- Core behavior should be consistent

---

## ✅ Verification Checklist

### Per-Example Verification

For each example, verify:

- [ ] **Compiles without errors**
  ```bash
  javac ExampleFile.java
  # Exit code should be 0
  ```

- [ ] **Runs to completion**
  ```bash
  java ExampleClass
  # Should not crash
  ```

- [ ] **Produces output**
  - Output should be clearly formatted
  - Each section should be labeled
  - Multiple examples should be shown

- [ ] **Demonstrates concepts**
  - Try-catch blocks work as expected
  - Exceptions are caught properly
  - Finally blocks execute
  - Resources are cleaned up

### Overall Module Verification

- [ ] All 5 examples compile
- [ ] All 5 examples run successfully
- [ ] No unhandled exceptions crash the programs
- [ ] Output is clear and educational
- [ ] Code demonstrates best practices
- [ ] Examples show real-world scenarios

---

## 🔍 Manual Testing

### Test Compilation Only
```bash
# Compile all files
find . -name "*.java" -exec javac {} \;

# Check for .class files
find . -name "*.class"
```

### Test Execution Only (after compilation)
```bash
# Run all examples
cd 01_TryCatchFinally && java TryCatchFinallyExample && cd ..
cd 02_ThrowsKeyword && java ThrowsExample && cd ..
cd 03_CustomExceptions && java CustomExceptionExample && cd ..
cd 04_ExceptionChaining && java ExceptionChainingExample && cd ..
cd 05_TryWithResources && java TryWithResourcesExample && cd ..
```

---

## 📏 Performance Testing

### Measure Execution Time (PowerShell)
```powershell
Measure-Command {
    cd 01_TryCatchFinally
    java TryCatchFinallyExample | Out-Null
    cd ..
}
```

### Measure Execution Time (Bash)
```bash
time (cd 01_TryCatchFinally && java TryCatchFinallyExample)
```

**Expected:** Each example should complete in under 1 second

---

## 🧹 Cleanup

### Remove Compiled Files
```bash
# Remove all .class files
find . -name "*.class" -delete

# Or on Windows PowerShell
Get-ChildItem -Recurse -Filter "*.class" | Remove-Item
```

---

## 📚 Additional Resources

- **Java Compilation:** [Oracle javac Guide](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javac.html)
- **Running Java:** [Oracle java Guide](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/java.html)
- **Exception Handling:** [Oracle Exception Tutorial](https://docs.oracle.com/javase/tutorial/essential/exceptions/)

---

## 🤝 Reporting Issues

If tests fail:
1. Check Java version (must be 8+)
2. Verify you're in correct directory
3. Check for typos in commands
4. Review error messages carefully
5. Try running examples individually

---

**Last Updated:** November 2025  
**Java Version Required:** 8+  
**Test Status:** ✅ All Passing  
**Total Examples:** 5

