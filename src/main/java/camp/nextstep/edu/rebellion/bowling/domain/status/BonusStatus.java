package camp.nextstep.edu.rebellion.bowling.domain.status;

import camp.nextstep.edu.rebellion.bowling.domain.score.FrameScore;

public class BonusStatus implements FrameStatus {
    private static final int STRIKE = 10;
    private static final int NO_HITS = 0;
    private static final String STRIKE_SYMBOL = "X";
    private static final String NO_HITS_SYMBOL = "-";

    private final FrameScore score;

    public BonusStatus(FrameScore score) {
        this.score = score;
    }

    @Override
    public String makeSymbol() {
        return wrapSymbol(score.getFirstScore()) + "|" +
                wrapSymbol(score.getLastScore());
    }

    private String wrapSymbol(int score) {
        return STRIKE == score ? STRIKE_SYMBOL :
                NO_HITS == score ? NO_HITS_SYMBOL :
                        String.valueOf(score);
    }
}
