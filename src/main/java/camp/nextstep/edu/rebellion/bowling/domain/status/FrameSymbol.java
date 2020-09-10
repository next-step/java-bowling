package camp.nextstep.edu.rebellion.bowling.domain.status;

public class FrameSymbol {
    private static final int STRIKE = 10;
    private static final int NO_HITS = 0;
    public static final String STRIKE_SYMBOL = "X";
    public static final String GUTTER_SYMBOL = "-";

    private FrameSymbol() {}

    public static String of(int score) {
        if (STRIKE == score) {
            return STRIKE_SYMBOL;
        }

        if (NO_HITS == score) {
            return GUTTER_SYMBOL;
        }

        return String.valueOf(score);
    }
}
