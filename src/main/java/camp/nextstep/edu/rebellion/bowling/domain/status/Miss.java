package camp.nextstep.edu.rebellion.bowling.domain.status;

public class Miss implements FrameStatus {
    private static final String GUTTER_SYMBOL = "-";
    private final int first;
    private final int last;

    public Miss(int first, int last) {
        this.first = first;
        this.last = last;
    }

    private String ifCutter(int score) {
        if (0 == score) {
            return GUTTER_SYMBOL;
        }
        return String.valueOf(score);
    }

    @Override
    public String makeSymbol() {
        return ifCutter(first) + "|" + ifCutter(last);
    }
}
