package camp.nextstep.edu.rebellion.bowling.domain.status;

import camp.nextstep.edu.rebellion.bowling.domain.score.FrameScore;

public class Spare implements FrameStatus {
    private static final String SPARE_SYMBOL = "|/";
    private final FrameScore score;

    public Spare(FrameScore score) {
        this.score = score;
    }

    @Override
    public String makeSymbol() {
        return score.getFirstScore() + SPARE_SYMBOL;
    }
}
