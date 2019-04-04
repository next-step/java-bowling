package domain;

import domain.state.BowlState;

import java.util.ArrayList;
import java.util.List;

import static domain.Frame.MAXIMUM_FRAME;
import static domain.Frame.MINIMUM_FRAME;

public class NormalFrame {

    protected int number;
    private BowlState state;
    private List<Pins> pinsOfFrame = new ArrayList<>();

    public NormalFrame(int number) {
        if(number < MINIMUM_FRAME || number >= MAXIMUM_FRAME) {
            throw new IllegalArgumentException();
        }

        this.number = number;
    }

    protected BowlState bowl(int pins) {
        return state.bowl(new Pins(pins));
    }

    public int getScore() {
        return pinsOfFrame.stream()
                    .mapToInt(Pins::getNumber)
                    .sum();
    }
}