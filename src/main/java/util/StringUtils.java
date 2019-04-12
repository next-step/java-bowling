package util;

import java.util.Optional;

public class StringUtils {
    public static boolean isEmpty(String s) {
        return s == null || s.length() <= 0;
    }

    public static String center(String s, int size) {
        return center(s, size, ' ');
    }

    private static String center(String s, int size, char pad) {
        if (s == null || size <= s.length())
            return s;

        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < (size - s.length()) / 2; i++) {
            sb.append(pad);
        }
        sb.append(s);
        while (sb.length() < size) {
            sb.append(pad);
        }
        return sb.toString();
    }

    public static String removeWhitespace(String string) {
        return Optional.ofNullable(string)
                .orElse("")
                .replaceAll("\\s+", "");
    }
}
