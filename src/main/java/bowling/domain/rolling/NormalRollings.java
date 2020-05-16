package bowling.domain.rolling;

import bowling.domain.exception.InvalidThrowBallException;
import bowling.domain.frame.State;

import java.util.ArrayList;
import java.util.List;

public class NormalRollings {
    private static final String TRY_ROLLING_OVER_MAX_COUNT_MESSAGE = "2번의 투구까지 가능합니다!";
    private static final int ROLLING_COUNT_INITIAL_VALUE = 0;
    private static final int ROLLING_COUNT_MAX_VALUE = 2;

    private int rollingCount;
    private List<Rolling> rollingList;

    private NormalRollings() {
        this.rollingCount = ROLLING_COUNT_INITIAL_VALUE;
        this.rollingList = new ArrayList<>();
    }

    public static NormalRollings init() {
        return new NormalRollings();
    }

    public void roll(int pinCount) {
        if (isRollingEnd()) {
            throw new InvalidThrowBallException(TRY_ROLLING_OVER_MAX_COUNT_MESSAGE);
        }

        if (!isRollingStarted()) {
            setFirstRolling(pinCount);
            return;
        }

        setSecondRolling(pinCount);
    }

    private void setFirstRolling(int pinCount) {
        State state = State.valueOf(pinCount);
        Rolling rolling = new Rolling(state, pinCount);

        rollingList.add(rolling);
        ++rollingCount;
    }

    private void setSecondRolling(int pinCount) {
        int remainPinCount = rollingList.get(getCurrentRollingIndex())
                .getRemainPinCount();
        State state = State.valueOf(remainPinCount, pinCount);
        Rolling rolling = new Rolling(state, pinCount);

        rollingList.add(rolling);
        ++rollingCount;
    }

    public boolean isRollingPossible() {
        if (!isRollingStarted()) {
            return true;
        }

        return !isRollingEnd();
    }

    private boolean isRollingStarted() {
        return rollingCount > ROLLING_COUNT_INITIAL_VALUE;
    }

    private boolean isRollingEnd() {
        if (rollingCount >= ROLLING_COUNT_MAX_VALUE) {
            return true;
        }

        State lastRollingState = rollingList.get(getCurrentRollingIndex()).getState();
        return lastRollingState == State.STRIKE || lastRollingState == State.SPARE;
    }

    public boolean isState(State state) {
        if (rollingCount == ROLLING_COUNT_INITIAL_VALUE) {
            return false;
        }

        Rolling lastRolling = rollingList.get(getCurrentRollingIndex());
        return lastRolling.getState() == state;
    }

    private int getCurrentRollingIndex() {
        return rollingCount - 1;
    }
}
