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

    public static String formatInt(final Integer integer, final String formatExp) {
        return format(String.valueOf(integer), formatExp);
    }
}
