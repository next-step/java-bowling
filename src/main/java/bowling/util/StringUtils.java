package bowling.util;

public class StringUtils {
    private StringUtils() {
        //no instance
    }

    public static String center(String text, int length) {
        String out = String.format("%" + length + "s%s%" + length + "s", "", text, "");
        float mid = (out.length() / 2);
        float start = mid - (length / 2);
        float end = start + length;
        return out.substring((int) start, (int) end);
    }
}
