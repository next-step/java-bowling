package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.frame.state.State;

import java.util.ArrayList;
import java.util.List;

public abstract class Frame {

    List<State> statetsOfHistory = new ArrayList<>();

    Frame initialize() {
        return NormalFrame.ofFirst();
    }

    Frame generate(FrameNumber number) {
        return number.isFinalNumber() ? FinalFrame.of() : NormalFrame.of(number);
    }

    abstract void bowl(Pins downPins);

    abstract Frame nextFrame();

    abstract boolean isGameOver();
}
