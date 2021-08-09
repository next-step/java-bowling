package bowling.domain.frame;

import bowling.domain.score.TurnScore;
import bowling.domain.turn.FirstTurn;
import bowling.domain.turn.SecondTurn;
import bowling.exception.BowlFailureException;

import java.util.Objects;

public class Frame {
    protected final FirstTurn firstTurn;
    protected final SecondTurn secondTurn;

    protected Frame(final FirstTurn firstTurn, final SecondTurn secondTurn) {
        this.firstTurn = firstTurn;
        this.secondTurn = secondTurn;
    }

    protected Frame(final FirstTurn firstTurn) {
        this(firstTurn, null);
    }

    public Frame() {
        this(null, null);
    }

    public Frame bowl(final TurnScore score) {
        if (isFirstTurn()) {
            return new Frame(
                    new FirstTurn(score)
            );
        }
        if (isSecondTurn()) {
            return new Frame(firstTurn, firstTurn.secondTurn(score));
        }

        throw new BowlFailureException();
    }

    private boolean isFirstTurn() {
        return Objects.isNull(firstTurn);
    }

    private boolean isSecondTurn() {
        return Objects.isNull(secondTurn);
    }

    public boolean isCompleted() {
        return !isFirstTurn() && !isSecondTurn();
    }

    public boolean isWaiting() {
        return !isCompleted();
    }

    public final boolean isStrike() {
        return firstTurn.isStrike();
    }

    public final boolean isSpare() {
        return secondTurn.isSpare();
    }

    public final boolean isMiss() {
        return firstTurn.isGutter() && secondTurn.isGutter();
    }
}
