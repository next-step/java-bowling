package bowling.util;

import java.util.stream.IntStream;

public class StringUtils {

    public static final String PAD = " ";

    private StringUtils() {}

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static String getCenterFormat(String str, int size) {
        if (str == null) {
            str = "";
        }
        return getCenterAligned(str, size);
    }

    private static String getCenterAligned(String str, int size) {
        StringBuilder sb = new StringBuilder(size);
        appendPad(sb, (size - str.length()) / 2);
        sb.append(str);
        appendPad(sb, (size - sb.length()));
        return sb.toString();
    }

    private static void appendPad(StringBuilder sb, int range) {
        IntStream.range(0, range)
                .forEach(index -> sb.append(PAD));
    }
}
