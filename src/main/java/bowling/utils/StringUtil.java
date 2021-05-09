package bowling.utils;

public final class StringUtil {
    private static final char DEFAULT_PAD = ' ';

    public static String center(final String s, final int length) {
        if (s == null || length <= s.length()) {
            return s;
        }

        final StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < (length - s.length()) / 2; i++) {
            sb.append(DEFAULT_PAD);
        }

        sb.append(s);

        while (sb.length() < length) {
            sb.append(DEFAULT_PAD);
        }

        return sb.toString();
    }
}