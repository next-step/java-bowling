package bowling.domain.rolling;

import bowling.common.exception.InvalidThrowBallException;
import bowling.domain.frame.Score;
import bowling.domain.state.StateFormat;

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

    private boolean isRollingEnd() {
        if (rollingList.size() >= ROLLING_COUNT_MAX_VALUE) {
            return true;
        }

        return isStrikeOrSpare();
    }

    @Override
    public boolean isRollingStarted() {
        return rollingList.size() > ROLLING_COUNT_INITIAL_VALUE;
    }

    private void setFirstRolling(int pinCount) {
        StateFormat stateFormat = StateFormat.valueOf(pinCount);
        Rolling rolling = new Rolling(stateFormat, pinCount);

        rollingList.add(rolling);
    }

    private void setSecondRolling(int pinCount) {
        int remainPinCount = rollingList.get(getCurrentRollingIndex())
                .getRemainPinCount();
        StateFormat stateFormat = StateFormat.valueOf(remainPinCount, pinCount);
        Rolling rolling = new Rolling(stateFormat, pinCount);

        rollingList.add(rolling);
    }

    @Override
    public boolean isRollingPossible() {
        if (!isRollingStarted()) {
            return true;
        }

        return !isRollingEnd();
    }
    public boolean isStrikeOrSpare() {
        if (!isRollingStarted()) {
            return false;
        }

        StateFormat lastRollingStateFormat = rollingList.get(getCurrentRollingIndex()).getStateFormat();
        return lastRollingStateFormat == StateFormat.STRIKE || lastRollingStateFormat == StateFormat.SPARE;
    }

    @Override
    public boolean isState(StateFormat stateFormat) {
        if (rollingList.size() == ROLLING_COUNT_INITIAL_VALUE) {
            return false;
        }

        Rolling lastRolling = rollingList.get(getCurrentRollingIndex());
        return lastRolling.isState(stateFormat);
    }

    private int getCurrentRollingIndex() {
        return rollingList.size() - 1;
    }

    @Override
    public List<String> getStates() {
        return rollingList.stream()
                .map(Rolling::getStateFormatValue)
                .collect(Collectors.toList());
    }

    @Override
    public int calculateScore() {
        return rollingList.stream()
                .mapToInt(Rolling::getKnockedDownPinCount)
                .sum();
    }

    @Override
    public void calculateAdditionalScore(Score score) {
        for (int i = 0; i < rollingList.size() && !score.isCalculateDone(); i++) {
            score.calculateAdditional(rollingList.get(i).getKnockedDownPinCount());
        }
    }
}
