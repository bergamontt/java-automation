package runtime;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class PathValidator {
    public static boolean isValidPath(String path) {
        String pathRegex = "(?i)^[a-z]:\\\\.*";
        Pattern pattern = Pattern.compile(pathRegex, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(path).matches();
    }

    public static boolean pathExists(String path) {
        if (path == null)
            return false;
        return Files.exists(Paths.get(path));
    }
}