package camp.nextstep.edu.rebellion.bowling.domain.status;

import camp.nextstep.edu.rebellion.bowling.domain.score.FrameScore;

public class Miss implements FrameStatus {
    private static final int NO_HITS = 0;
    private static final String GUTTER_SYMBOL = "-";

    private final FrameScore score;

    public Miss(FrameScore score) {
        this.score = score;
    }

    @Override
    public String makeSymbol() {
        return ifCutter(score.getFirstScore()) + "|" +
                ifCutter(score.getLastScore());
    }

    private String ifCutter(int score) {
        return NO_HITS == score ? GUTTER_SYMBOL :
                String.valueOf(score);
    }
}
