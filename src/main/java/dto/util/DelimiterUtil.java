package dto.util;

public class DelimiterUtil {
    private static final String DELIMITER = "|";

    public static String deleteBar(String raw) {
        return raw.replace(DELIMITER, "");
    }
}
