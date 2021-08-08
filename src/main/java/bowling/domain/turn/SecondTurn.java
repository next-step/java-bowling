package bowling.domain.turn;

import bowling.domain.score.TurnScore;
import bowling.domain.score.Score;

public final class SecondTurn extends Turn {
    private static final TurnScore SPARE_VALUE = TurnScore.max();

    private final FirstTurn firstFrame;

    protected SecondTurn(final FirstTurn firstFrame, final TurnScore score) {
        super(score);
        this.firstFrame = firstFrame;
    }

    public boolean isSpare() {
        if (isGutter()) {
            return false;
        }

        Score totalScore = this.score.sum(
                firstFrame.score
        );
        return SPARE_VALUE.equals(totalScore);
    }
}
