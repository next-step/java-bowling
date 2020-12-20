package bowling.util;

public class StringUtils {
    public StringUtils() {
    }

    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
