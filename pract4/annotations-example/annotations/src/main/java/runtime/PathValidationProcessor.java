package runtime;

import java.lang.reflect.Field;

public class PathValidationProcessor {

    public static void validate(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if(!field.isAnnotationPresent(ValidPath.class))
                continue;
            field.setAccessible(true);
            String filepath = (String) field.get(obj);
            if (!PathValidator.isValidPath(filepath) || !PathValidator.pathExists(filepath))
                throw new IllegalArgumentException(field.getAnnotation(ValidPath.class).message());
        }
    }

}