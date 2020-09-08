package camp.nextstep.edu.rebellion.bowling.domain.status;

import camp.nextstep.edu.rebellion.bowling.domain.score.FrameScore;

public class BonusStatus implements FrameStatus {
    private static final int STRIKE = 10;

    private final FrameScore score;

    public BonusStatus(FrameScore score) {
        this.score = score;
    }

    @Override
    public String makeSymbol() {
        return wrapSymbol(score.getFirstScore()) + "|" + wrapSymbol(score.getLastScore());
    }

    private String wrapSymbol(int hits) {
        if (STRIKE == hits) {
            return "X";
        }

        if (0 == hits) {
            return "-";
        }

        return String.valueOf(hits);
    }
}
