# Stream Performance

Best practices and optimization techniques for Stream API.

## Performance Tips

### DO ✅
- Use primitive streams (IntStream, LongStream, DoubleStream)
- Use parallel streams for large datasets
- Keep pipelines short
- Use method references

### DON'T ❌
- Modify source during operation
- Use parallel for small datasets
- Perform I/O in streams
- Create unnecessary intermediate objects

For details, see: **[StreamPerformanceExample.java](StreamPerformanceExample.java)**

