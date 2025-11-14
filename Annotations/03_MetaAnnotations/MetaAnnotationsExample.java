import java.lang.annotation.*;

public class MetaAnnotationsExample {

    public static void main(String[] args) {
        System.out.println("═══ META-ANNOTATIONS EXAMPLES ═══\n");

        retentionExample();
        targetExample();
        inheritedExample();

        System.out.println("\n═══ Complete! ═══");
    }

    static void retentionExample() {
        System.out.println("--- @Retention Example ---");

        @RuntimeAnnotation("Test")
        class Example { }

        Example ex = new Example();
        RuntimeAnnotation annotation = ex.getClass().getAnnotation(RuntimeAnnotation.class);

        if (annotation != null) {
            System.out.println("Runtime annotation value: " + annotation.value());
        }
        System.out.println();
    }

    static void targetExample() {
        System.out.println("--- @Target Example ---");

        @MethodOnly
        void testMethod() {
            System.out.println("Method with @MethodOnly annotation");
        }

        System.out.println("@Target restricts where annotation can be used");
        testMethod();
        System.out.println();
    }

    static void inheritedExample() {
        System.out.println("--- @Inherited Example ---");

        InheritedClass child = new InheritedClass();

        // Check if inherited annotation is present
        if (child.getClass().isAnnotationPresent(InheritableAnnotation.class)) {
            System.out.println("Child class inherited the annotation!");
            InheritableAnnotation annotation =
                child.getClass().getAnnotation(InheritableAnnotation.class);
            System.out.println("Value: " + annotation.value());
        }
        System.out.println();
    }
}

// Runtime retention
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface RuntimeAnnotation {
    String value();
}

// Target specific to methods
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MethodOnly {
}

// Inherited annotation
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface InheritableAnnotation {
    String value() default "inherited";
}

@InheritableAnnotation("Parent class annotation")
class ParentClass {
}

class InheritedClass extends ParentClass {
    // Inherits @InheritableAnnotation from ParentClass
}

