package domain.state;

import domain.Pins;

import java.util.ArrayList;
import java.util.List;

public class Bonus implements State {

    private final static int BONUS_FRAME_BOWL_LIMIT = 3;
    private final static int SECOND_BOWL = 2;

    private List<Pins> pins;
    private Score frameScore;

    public Bonus() {
        pins = new ArrayList<>();
    }

    @Override
    public State bowl(Pins downPins) {
        if( isLastBowl() || isEmptyWhenSecondBowl()) {
            throw new IllegalArgumentException("더이상 진행 할 수 없습니다.");
        }
        pins.add(downPins);
        return this;
    }

    @Override
    public Boolean isClosed() {
        return isLastBowl() || isEmptyWhenSecondBowl();
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

    private int getLastDownPins() {
        return pins.size() - 1;
    }
}
