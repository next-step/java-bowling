package bowling.domain.frame;

import bowling.domain.score.TurnScore;
import bowling.domain.turn.FirstTurn;
import bowling.domain.turn.SecondTurn;
import bowling.domain.turn.Turn;

import java.util.Objects;

public final class FinalFrame extends Frame {
    private final Turn bonusTurn;

    protected FinalFrame(final FirstTurn firstTurn, final SecondTurn secondTurn, final Turn bonusTurn) {
        super(firstTurn, secondTurn);
        this.bonusTurn = bonusTurn;
    }

    protected FinalFrame(final FirstTurn firstTurn, final SecondTurn secondTurn) {
        this(firstTurn, secondTurn, null);
    }

    public FinalFrame() {
        this(null, null, null);
    }

    @Override
    public Frame bowl(TurnScore score) {
        if (isBonusTurn()) {
            return new FinalFrame(firstTurn, secondTurn, new Turn(score));
        }
        Frame basicFrame = super.bowl(score);
        return new FinalFrame(basicFrame.firstTurn, basicFrame.secondTurn);
    }

    private boolean isBonusTurn() {
        return super.isCompleted()
                && Objects.isNull(bonusTurn)
                && (isStrike() || isSpare());
    }

    @Override
    public boolean isCompleted() {
        return super.isCompleted() && Objects.nonNull(bonusTurn);
    }
}
