import java.lang.annotation.*;

public class CustomAnnotationsExample {

    public static void main(String[] args) {
        System.out.println("═══ CUSTOM ANNOTATIONS EXAMPLES ═══\n");

        simpleAnnotationExample();
        annotationWithElementsExample();
        processingAnnotationsExample();

        System.out.println("\n═══ Complete! ═══");
    }

    static void simpleAnnotationExample() {
        System.out.println("--- Simple Custom Annotation ---");

        @Author(name = "Alice")
        class Book {
            String title = "Java Programming";
        }

        Book book = new Book();
        Author author = book.getClass().getAnnotation(Author.class);
        if (author != null) {
            System.out.println("Book author: " + author.name());
        }
        System.out.println();
    }

    static void annotationWithElementsExample() {
        System.out.println("--- Annotation with Multiple Elements ---");

        @Version(major = 1, minor = 5, patch = 2)
        class Application {
            String name = "MyApp";
        }

        Application app = new Application();
        Version version = app.getClass().getAnnotation(Version.class);
        if (version != null) {
            System.out.println("Version: " + version.major() + "." +
                             version.minor() + "." + version.patch());
        }
        System.out.println();
    }

    static void processingAnnotationsExample() {
        System.out.println("--- Processing Annotations at Runtime ---");

        TestClass testObj = new TestClass();

        // Check if class has @TestInfo annotation
        if (testObj.getClass().isAnnotationPresent(TestInfo.class)) {
            TestInfo info = testObj.getClass().getAnnotation(TestInfo.class);
            System.out.println("Test Priority: " + info.priority());
            System.out.println("Test Tags: " + String.join(", ", info.tags()));
        }
        System.out.println();
    }
}

// Simple annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Author {
    String name();
}

// Annotation with multiple elements
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Version {
    int major();
    int minor();
    int patch() default 0;
}

// Annotation with array
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface TestInfo {
    int priority() default 1;
    String[] tags() default {};
}

@TestInfo(priority = 5, tags = {"unit-test", "fast"})
class TestClass {
    void testMethod() {
        System.out.println("Test running");
    }
}

