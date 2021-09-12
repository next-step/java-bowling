package bowling.util;

public class OutputStringFormatter {
    private static final String SPACE = " ";

    public static String toCenterAlignedWithFixedPaddedString(String value, int width) {
        int spaceSize = width - value.length();
        int prefixSize = spaceSize / 2;
        int suffixSize = (spaceSize + 1) / 2;

        if (width > value.length()) {
            return getSpace(prefixSize) + value + getSpace(suffixSize);
        }

        return value;
    }

    private static String getSpace(int size) {
        return new String(new char[size]).replace("\0", SPACE);
    }
}
