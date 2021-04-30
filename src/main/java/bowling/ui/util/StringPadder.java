package bowling.ui.util;

public class StringPadder {
    public static String addPadding(String string) {
        if (string.length() == 0) {
            return "      ";
        }
        if (string.length() == 1) {
            return "  " + string + "   ";
        }
        if (string.length() == 2) {
            return "  " + string + "  ";
        }
        if (string.length() == 3) {
            return "  " + string + " ";
        }
        if (string.length() == 5) {
            return " " + string;
        }
        return string;
    }
}
