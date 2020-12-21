package bowling.common;

public class StringUtils {
    private static final int NUMBER_OF_PADDING = 6;
    private static final char BLANK = ' ';

    private StringUtils() {}

    public static String centerPadding(String inputString) {
        if (inputString == null || NUMBER_OF_PADDING <= inputString.length())
            return inputString;

        StringBuilder sb = new StringBuilder(NUMBER_OF_PADDING);
        for (int i = 0; i < (NUMBER_OF_PADDING - inputString.length() + 1) / 2; i++) {
            sb.append(BLANK);
        }
        sb.append(inputString);
        while (sb.length() < NUMBER_OF_PADDING) {
            sb.append(BLANK);
        }
        return sb.toString();
    }
}
