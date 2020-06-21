package bowling.util;

public class StringUtil {

    private StringUtil() {
    }

    public static boolean isEmpty(final String str) {
        return (str == null || str.isEmpty());
    }
}
