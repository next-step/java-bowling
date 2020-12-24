package bowling_step3.util;

public class StringUtils {
    private StringUtils() {
    }

    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
