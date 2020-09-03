package camp.nextstep.edu.rebellion.bowling.domain.status;

import camp.nextstep.edu.rebellion.bowling.domain.score.FrameScore;

public class Miss implements FrameStatus {
    private static final String GUTTER_SYMBOL = "-";

    private final FrameScore score;

    public Miss(FrameScore score) {
        this.score = score;
    }

    private String ifCutter(int score) {
        if (0 == score) {
            return GUTTER_SYMBOL;
        }
        return String.valueOf(score);
    }

    @Override
    public String makeSymbol() {
        return ifCutter(score.getFirstScore()) + "|" + ifCutter(score.getLastScore());
    }
}
