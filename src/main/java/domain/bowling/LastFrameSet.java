package domain.bowling;

import domain.Pins;
import domain.state.State;
import domain.state.StateFactory;

import java.util.ArrayList;
import java.util.List;

public class LastFrameSet implements Bowling {

    private final static int BONUS_FRAME_BOWL_LIMIT = 3;
    private final static int SECOND_BOWL = 2;

    private List<Pins> pins = new ArrayList<>();
    private State state;

    public LastFrameSet() {

    }

    @Override
    public Bowling bowl(Pins downPins) {
        if (isLastBowl() || isEmptyWhenSecondBowl()) {
            throw new IllegalArgumentException("더이상 진행 할 수 없습니다.");
        }
        state = getState(downPins);
        pins.add(downPins);
        return this;
    }

    @Override
    public State getFrameState() {
        return state;
    }

    private State getState(Pins downPins) {
        if (pins.isEmpty()) {
            return StateFactory.firstState(downPins);
        }
        Pins pins = this.pins.get(this.pins.size() - 1);
        if (Pins.ALL.equals(pins)) {
            return StateFactory.firstState(downPins);
        }
        return StateFactory.secondState(pins, downPins);
    }

    private boolean isLastBowl() {
        return pins.size() == BONUS_FRAME_BOWL_LIMIT;
    }

    private boolean isEmptyWhenSecondBowl() {
        return pins.size() == SECOND_BOWL && addAll() < Pins.ALL.value();
    }

    private int addAll() {
        return this.pins.stream()
                .mapToInt(Pins::value)
                .sum();
    }
}
