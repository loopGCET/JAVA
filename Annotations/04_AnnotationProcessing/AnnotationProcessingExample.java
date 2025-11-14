import java.lang.annotation.*;
import java.lang.reflect.*;

public class AnnotationProcessingExample {

    public static void main(String[] args) {
        System.out.println("═══ ANNOTATION PROCESSING EXAMPLES ═══\n");

        processClassAnnotations();
        processMethodAnnotations();
        processFieldAnnotations();

        System.out.println("\n═══ Complete! ═══");
    }

    static void processClassAnnotations() {
        System.out.println("--- Processing Class Annotations ---");

        Service service = new Service();
        Class<?> clazz = service.getClass();

        if (clazz.isAnnotationPresent(Component.class)) {
            Component component = clazz.getAnnotation(Component.class);
            System.out.println("Component name: " + component.name());
            System.out.println("Singleton: " + component.singleton());
        }
        System.out.println();
    }

    static void processMethodAnnotations() {
        System.out.println("--- Processing Method Annotations ---");

        UserController controller = new UserController();
        Method[] methods = controller.getClass().getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping mapping = method.getAnnotation(RequestMapping.class);
                System.out.println("Method: " + method.getName());
                System.out.println("  Path: " + mapping.path());
                System.out.println("  HTTP Method: " + mapping.method());
            }
        }
        System.out.println();
    }

    static void processFieldAnnotations() {
        System.out.println("--- Processing Field Annotations ---");

        User user = new User();
        Field[] fields = user.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                System.out.println("Field: " + field.getName());
                System.out.println("  Column name: " + column.name());
                System.out.println("  Nullable: " + column.nullable());
            }
        }
        System.out.println();
    }
}

// Custom annotations for processing

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Component {
    String name();
    boolean singleton() default true;
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RequestMapping {
    String path();
    String method() default "GET";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name();
    boolean nullable() default true;
}

// Example classes with annotations

@Component(name = "UserService", singleton = true)
class Service {
    void execute() {
        System.out.println("Service executing");
    }
}

class UserController {

    @RequestMapping(path = "/users", method = "GET")
    public void getUsers() {
        System.out.println("Getting users");
    }

    @RequestMapping(path = "/users", method = "POST")
    public void createUser() {
        System.out.println("Creating user");
    }
}

class User {
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "email_address", nullable = false)
    private String email;
}

