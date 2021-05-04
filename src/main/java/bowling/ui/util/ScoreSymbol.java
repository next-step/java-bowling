package bowling.ui.util;

public class ScoreSymbol {
    public static String convert(int value) {
        if (value == 10) {
            return "X";
        }
        if (value == 0) {
            return "-";
        }
        if (value == -1) {
            return "/";
        }
        return String.valueOf(value);
    }
}
