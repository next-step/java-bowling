package bowling.utils;

public class StringUtil {

    private StringUtil() {
        throw new IllegalStateException();
    }

    public static boolean isBlank(String text) {
        return text == null || text.trim().isEmpty();
    }

    public static String pad(int score, int margin) {
        StringBuilder sb = new StringBuilder();
        String s = String.valueOf(score);
        for (int i = s.length(); i < margin; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
