#!/bin/bash

# Java Exception Handling - Complete Test Script
# Tests all examples in the module

echo "=========================================="
echo "Java Exception Handling - Test Suite"
echo "=========================================="
echo ""

total_tests=0
passed_tests=0
failed_tests=0

# Array of topics to test
topics=(
    "01_TryCatchFinally:TryCatchFinallyExample"
    "02_ThrowsKeyword:ThrowsExample"
    "03_CustomExceptions:CustomExceptionExample"
    "04_ExceptionChaining:ExceptionChainingExample"
    "05_TryWithResources:TryWithResourcesExample"
)

# Test each topic
for topic_info in "${topics[@]}"; do
    IFS=':' read -r folder classname <<< "$topic_info"

    echo "Testing: $folder"
    ((total_tests++))

    cd "$folder" 2>/dev/null || {
        echo "  ❌ Folder not found"
        ((failed_tests++))
        continue
    }

    # Compile
    if javac "${classname}.java" 2>/dev/null; then
        echo "  ✅ Compilation passed"
    else
        echo "  ❌ Compilation FAILED"
        ((failed_tests++))
        cd ..
        continue
    fi

    # Run
    if java "$classname" >/dev/null 2>&1; then
        echo "  ✅ Execution passed"
        ((passed_tests++))
    else
        echo "  ❌ Execution FAILED"
        ((failed_tests++))
    fi

    cd ..
    echo ""
done

echo "=========================================="
echo "Test Summary"
echo "=========================================="
echo "Total Tests: $total_tests"
echo "Passed: $passed_tests"
echo "Failed: $failed_tests"
echo ""

if [ $failed_tests -eq 0 ]; then
    echo "✅ All tests passed!"
    exit 0
else
    echo "❌ Some tests failed"
    exit 1
fi

