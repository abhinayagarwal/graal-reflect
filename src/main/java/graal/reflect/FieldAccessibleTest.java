package graal.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class FieldAccessibleTest {

    public static void main(String[] args) {
        updateFieldAccessibility(Model.class, "title");
    }
    
    public static <T> void updateFieldAccessibility(Class<T> klass, String fieldName) {
        try {
            Field field = klass.getDeclaredField(fieldName);
            T ti = klass.getDeclaredConstructor().newInstance();
            if (!field.canAccess(ti)) {
                if (!field.trySetAccessible()) {
                    System.out.println("Could not make inaccessible field accessible");
                }
            }
            System.out.println("Success");
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException e) {
            System.out.println("Failure");
        }
    }
}