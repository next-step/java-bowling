package bowling.domain.frame;

import bowling.domain.score.TurnScore;
import bowling.domain.turn.FirstTurn;
import bowling.domain.turn.SecondTurn;
import bowling.exception.BowlFailureException;

import java.util.ArrayList;
import java.util.List;
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
        if (currentFirstTurn()) {
            return new Frame(
                    new FirstTurn(score)
            );
        }
        if (currentSecondTurn()) {
            return new Frame(firstTurn, firstTurn.secondTurn(score));
        }

        throw new BowlFailureException();
    }

    private boolean currentFirstTurn() {
        return Objects.isNull(firstTurn);
    }

    private boolean currentSecondTurn() {
        return !isStrike() && Objects.isNull(secondTurn);
    }

    public boolean isCompleted() {
        if (isStrike()) {
            return true;
        }
        return !currentFirstTurn() && !currentSecondTurn();
    }

    public boolean isWaiting() {
        return !isCompleted();
    }

    public final boolean isStrike() {
        if (Objects.isNull(firstTurn)) {
            return false;
        }
        return firstTurn.isStrike();
    }

    public final boolean isSpare() {
        if (Objects.isNull(secondTurn)) {
            return false;
        }
        return secondTurn.isSpare();
    }

    public final boolean isMiss() {
        if (Objects.isNull(firstTurn) && Objects.isNull(secondTurn)) {
            return false;
        }
        return firstTurn.isGutter() && secondTurn.isGutter();
    }

    public List<TurnScore> scores() {
        List<TurnScore> scores = new ArrayList<>();

        if (Objects.nonNull(firstTurn)) {
            scores.add(firstTurn.score());
        }
        if (Objects.nonNull(secondTurn)) {
            scores.add(secondTurn.score());
        }
        return scores;
    }
}
