package bowling.utils;

public class StringUtil {

    private StringUtil() {
        throw new IllegalStateException();
    }

    public static boolean isBlank(String text) {
        return text == null || text.trim().isEmpty();
    }

    public static String pad(int position, int margin, String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = position; i < margin; i++) {
            sb.append(text);
        }
        return sb.toString();
    }
}
