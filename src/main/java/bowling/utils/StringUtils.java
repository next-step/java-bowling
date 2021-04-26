package bowling.utils;

public final class StringUtils {

    private StringUtils() {}

    public static String padLeft(String padString, int size) {
        return String.format("%" + size + "s", padString);
    }

    public static String alignCenter(String padString, int size) {
        if (padString == null) {
            return alignCenter("null", size);
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
            centerBuilder.append(" ");
        }
        centerBuilder.append(padString);
        while (centerBuilder.length() < size) {
            centerBuilder.append(" ");
        }
        return centerBuilder;
    }
}
