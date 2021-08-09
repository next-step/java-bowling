package bowling.domain.frame;

import bowling.domain.score.TurnScore;
import bowling.domain.turn.FirstTurn;
import bowling.domain.turn.SecondTurn;
import bowling.domain.turn.Turn;

import java.util.List;
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
        if (currentBonusTurn()) {
            return new FinalFrame(firstTurn, secondTurn, new Turn(score));
        }
        Frame basicFrame = super.bowl(score);
        return new FinalFrame(basicFrame.firstTurn, basicFrame.secondTurn);
    }

    private boolean currentBonusTurn() {
        return super.isCompleted()            // 기본 턴이 완료 되어야 하고
                && isAvailableBonusTurn()     // 스트라이크나 스페어여야 하며
                && Objects.isNull(bonusTurn); // 보너스 턴을 이미 진행하지 않았어야 한다.
    }

    @Override
    public boolean isCompleted() {
        return super.isCompleted()
                && (isAvailableBonusTurn() ? Objects.nonNull(bonusTurn) : Objects.isNull(bonusTurn));
    }

    private boolean isAvailableBonusTurn() {
        return isStrike() || isSpare();
    }

    public TurnScore bonusScore() {
        return bonusTurn.score();
    }
}
