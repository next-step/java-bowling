package bowling.utils;

public final class StringUtils {

    public static final String BLANK_STRING = " ";

    private StringUtils() {}

    public static String padLeft(String padString, int size) {
        return String.format("%" + size + "s", padString);
    }

    public static String alignCenter(String padString, int size) {
        if (padString == null) {
            return alignCenter(String.valueOf(padString), size);
        }

        if (size <= padString.length()) {
            return padString;
        }

        StringBuilder centerBuilder = buildCenterAlignedString(padString, size);
        return centerBuilder.toString();
    }

    private static StringBuilder buildCenterAlignedString(String padString, int size) {
        StringBuilder centerBuilder = new StringBuilder(size);

        final int leftSize = (size - padString.length()) / 2;
        for (int i = 0; i < leftSize; i++) {
            centerBuilder.append(BLANK_STRING);
        }
        centerBuilder.append(padString);
        while (centerBuilder.length() < size) {
            centerBuilder.append(BLANK_STRING);
        }
        return centerBuilder;
    }
}
