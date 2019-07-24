package utils;

public class PrintUtils {
    private static final int FRAME_SIZE = 6;
    private static final int PREFIX_SIZE = 2;
    private static final String PREFIX = "  ";
    private static final String PIECE_OF_SUFFIX = " ";
    private static final int FIRST_INDEX_OF_PREFIX = 0;

    public static String centralize(String data) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PREFIX);
        if (data.length() + PREFIX_SIZE > FRAME_SIZE) {
            stringBuilder.delete(FIRST_INDEX_OF_PREFIX, data.length() + PREFIX_SIZE - FRAME_SIZE);
        }
        stringBuilder.append(data);

        int dataSize = data.length();
        int suffixSize = FRAME_SIZE - (PREFIX_SIZE + dataSize);
        for (int i = 0; i < suffixSize; i++) {
            stringBuilder.append(PIECE_OF_SUFFIX);
        }
        return stringBuilder.toString();
    }
}
