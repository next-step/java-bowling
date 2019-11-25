package bowling;

public class StringUtils {

    public static final String BLANK = " ";

    private StringUtils() {
    }

    public static String addBlank(String text, int totalLength) {
        return addBlank(new StringBuilder(text), totalLength);
    }

    public static String addBlank(StringBuilder sb, int totalLength) {
        int blankLength = (totalLength - sb.length() + 1) / 2;

        for (int i = 0; i < blankLength; i++) {
            sb.append(BLANK);
            sb.insert(0, BLANK);
        }

        if (sb.length() > totalLength) {
            return sb.substring(0, totalLength);
        }

        return sb.toString();
    }
}
