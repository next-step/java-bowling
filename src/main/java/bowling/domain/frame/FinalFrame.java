package bowling.domain.frame;

import bowling.domain.engine.State;
import bowling.domain.pin.BonusPin;
import bowling.domain.pin.Pins;
import bowling.domain.state.finish.Spare;
import bowling.domain.state.finish.Strike;
import bowling.domain.state.run.Hit;
import bowling.exception.CanNotBonusException;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static bowling.domain.Constants.*;
import static bowling.domain.pin.Pin.BOWLING_PIN_MAX_SIZE;

public class FinalFrame extends BaseFrame {

    private final List<State> status = new ArrayList<>(BOWLING_FINAL_TRY);
    private BonusPin bonusPin;
    private int tryCount;

    public FinalFrame() {
        super(BOWLING_LAST_ROUND);
        tryCount = BOWLING_FIRST_TRY;
    }

    // core 로직
    public void bowl(final int downPins) {
        checkBonusChange();

        if (isBonusChance()) {
            bonusPin = BonusPin.of(downPins);
        }

        if (isNormalChance()) {
            pins = bowling(downPins);
        }
        changeBowlingState(downPins);
    }

    private Pins bowling(final int downPins) {
        if (tryCount == BOWLING_FIRST_TRY) {
            return pins.first(downPins);
        }
        return pins.last(downPins);
    }

    private void changeBowlingState(final int downPins) {
        state = state.bowl(downPins);
        status.add(state);
        tryCount = tryCount + 1;
    }

    // core end

    // condition

    private void checkBonusChange() {
        if (tryCount > BOWLING_SECOND_TRY && !isBonusState()) {
            throw new CanNotBonusException();
        }
    }

    private boolean isBonusState() {
        return status.stream()
                .anyMatch(this::isStrikeOrSpare);
    }

    private boolean isStrikeOrSpare(final State state) {
        return isState(state, Strike.class) || isState(state, Spare.class);
    }

    private boolean isNormalChance() {
        return tryCount < BOWLING_FINAL_TRY;
    }

    private boolean isBonusChance() {
        return tryCount == BOWLING_SECOND_TRY
                && pins.normalScore() >= BOWLING_PIN_MAX_SIZE;
    }

    public boolean isEnd() {
        return (tryCount == BOWLING_FINAL_TRY && !isBonusState())
                || tryCount > BOWLING_FINAL_TRY;
    }

    private boolean isExtractState(final State state) {
        return !isState(state, Hit.class);
    }

    private boolean isState(final State state, Class<?> classType) {
        return state.getClass().isAssignableFrom(classType);
    }

    public List<State> status() {
        return Collections.unmodifiableList(status);
    }

    @Override
    public int score() {
        return pins.normalScore() + bonusPin.score();
    }

    @Override
    public boolean isNextFrame() {
        return false;
    }

    @Override
    public String printFrame() {
        if (tryCount > BOWLING_SECOND_TRY) {
            return status.stream()
                    .filter(this::isExtractState)
                    .map(State::printLastResult)
                    .collect(Collectors.joining(BOWLING_STATE_SPLIT_DELIMITER, Strings.EMPTY, Strings.EMPTY));
        }
        return status.stream()
                .map(State::printResult)
                .collect(Collectors.joining(BOWLING_STATE_SPLIT_DELIMITER, Strings.EMPTY, Strings.EMPTY));
    }
}
