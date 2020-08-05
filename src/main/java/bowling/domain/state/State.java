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
    public static int PIN_COUNT_GUTTER = 0;

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

        boolean isRollStarted = rollings.isRollingStarted();
        rollings.roll(knockedDownPinCount);

        return checkStateByPinCount(knockedDownPinCount, isRollStarted);
    }

    private State checkStateByPinCount(int knockedDownPinCount, boolean isRollingStarted) {
        int nextRemainPinCount = checkNextRemainPinCount(knockedDownPinCount);

        if (!isRollingStarted && remainPinCount == PIN_COUNT_INITIAL && knockedDownPinCount == remainPinCount) {
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

        if (nextRemainPinCount == 0 && isTurnToBonusRolling()) {
            return PIN_COUNT_INITIAL;
        }
        return nextRemainPinCount;
    }

    private boolean isTurnToBonusRolling() {
        return rollings instanceof FinalRollings && rollings.isRollingPossible();
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
