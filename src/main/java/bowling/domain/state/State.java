package bowling.domain.state;

import bowling.common.exception.InvalidThrowBallException;
import bowling.domain.frame.Score;
import bowling.domain.rolling.FinalRollings;
import bowling.domain.rolling.NormalRollings;
import bowling.domain.rolling.Rollings;
import bowling.domain.state.exception.RollingPinCountException;

import java.util.List;

public abstract class State {
    public static String KNOCKED_DOWN_PIN_EXCEPTION_MESSAGE = "남은 핀의 수 보다 많은 핀을 쓰러트릴 수 없습니다!";
    public static int PIN_COUNT_INITIAL = 10;
    public static int PIN_COUNT_GUTTER = 10;

    Rollings rollings;
    int remainPinCount;

    public State(boolean isFinalFrame) {
        this.remainPinCount = PIN_COUNT_INITIAL;
        if (isFinalFrame) {
            this.rollings = FinalRollings.init();
            return;
        }
        this.rollings = NormalRollings.init();
    }

    State(Rollings rollings, int remainPinCount) {
        this.rollings = rollings;
        this.remainPinCount = remainPinCount;
    }

    public State roll(int knockedDownPinCount) {
        if (remainPinCount < knockedDownPinCount) {
            throw new RollingPinCountException(KNOCKED_DOWN_PIN_EXCEPTION_MESSAGE);
        }

        if (!rollings.isRollingPossible()) {
            throw new InvalidThrowBallException();  // TODO: 2020/08/06 다른 예외처리로 변경할 것
        }

        rollings.roll(knockedDownPinCount);

        return checkStateByPinCount(knockedDownPinCount);
    }

    private State checkStateByPinCount(int knockedDownPinCount) {
        int nextRemainPinCount = checkNextRemainPinCount(knockedDownPinCount);

        if (remainPinCount == PIN_COUNT_INITIAL && knockedDownPinCount == PIN_COUNT_INITIAL) {
            return new Strike(rollings, nextRemainPinCount);
        }

        if (remainPinCount == knockedDownPinCount) {
            return new Spare(rollings, nextRemainPinCount);
        }

        if (knockedDownPinCount == PIN_COUNT_GUTTER) {
            return new Gutter(rollings, nextRemainPinCount);
        }

        return new Miss(rollings, nextRemainPinCount);
    }

    private int checkNextRemainPinCount(int knockedDownPinCount) {
        int nextRemainPinCount = remainPinCount - knockedDownPinCount;

        if (nextRemainPinCount == 0 && rollings instanceof FinalRollings && rollings.isRollingPossible()) {
            return PIN_COUNT_INITIAL;
        }
        return nextRemainPinCount;
    }

    public abstract Score calculateScore();
    public abstract Score calculateScore(int lastFrameScore);

    public boolean isRollingPossible() {
        return rollings.isRollingPossible();
    }

    public List<String> getStates() {
        return rollings.getStates();
    }

    public void calculateAdditionalScore(Score score) {
        rollings.calculateAdditionalScore(score);
    }
}
