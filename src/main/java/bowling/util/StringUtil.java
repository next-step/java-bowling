package bowling.util;

public class StringUtil {

    public static final String EMPTY = "";

    private StringUtil() {
    }

    public static boolean isEmpty(final String str) {
        return (str == null || str.isEmpty());
    }

    public static String format(final String str, final String formatExp) {
        return String.format(formatExp, str);
    }
}
