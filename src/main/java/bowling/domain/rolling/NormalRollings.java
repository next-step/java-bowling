package bowling.domain.rolling;

import bowling.common.exception.InvalidThrowBallException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NormalRollings implements Rollings {
    private static final String TRY_ROLLING_OVER_MAX_COUNT_MESSAGE = "2번의 투구까지 가능합니다!";
    private static final int ROLLING_COUNT_INITIAL_VALUE = 0;
    private static final int ROLLING_COUNT_MAX_VALUE = 2;

    private final List<Rolling> rollingList;

    private NormalRollings() {
        this.rollingList = new ArrayList<>();
    }

    public static NormalRollings init() {
        return new NormalRollings();
    }

    @Override
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
    }

    private void setSecondRolling(int pinCount) {
        int remainPinCount = rollingList.get(getCurrentRollingIndex())
                .getRemainPinCount();
        State state = State.valueOf(remainPinCount, pinCount);
        Rolling rolling = new Rolling(state, pinCount);

        rollingList.add(rolling);
    }

    @Override
    public boolean isRollingPossible() {
        if (!isRollingStarted()) {
            return true;
        }

        return !isRollingEnd();
    }

    private boolean isRollingStarted() {
        return rollingList.size() > ROLLING_COUNT_INITIAL_VALUE;
    }

    private boolean isRollingEnd() {
        if (rollingList.size() >= ROLLING_COUNT_MAX_VALUE) {
            return true;
        }

        return isStrikeOrSpare();
    }

    public boolean isStrikeOrSpare() {
        if (!isRollingStarted()) {
            return false;
        }

        State lastRollingState = rollingList.get(getCurrentRollingIndex()).getState();
        return lastRollingState == State.STRIKE || lastRollingState == State.SPARE;
    }

    public boolean isState(State state) {
        if (rollingList.size() == ROLLING_COUNT_INITIAL_VALUE) {
            return false;
        }

        Rolling lastRolling = rollingList.get(getCurrentRollingIndex());
        return lastRolling.isState(state);
    }

    private int getCurrentRollingIndex() {
        return rollingList.size() - 1;
    }

    @Override
    public List<String> getStates() {
        return rollingList.stream()
                .map(Rolling::getStateFormat)
                .collect(Collectors.toList());
    }
}
