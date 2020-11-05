package bowling.frame;

import bowling.frame.state.Final;
import bowling.frame.state.State;
import bowling.frame.state.Strike;
import bowling.score.Pin;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame extends Frame {

    private final LinkedList<State> states = new LinkedList<>();

    private FinalFrame() {
        super(FINAL_FRAME_NUMBER);
        states.add(super.state);
    }

    public static Frame create() {
        return new FinalFrame();
    }

    @Override
    protected Frame bowl(String fellPins) {
        State currentState = states.getLast();
         Pin pin = Pin.bowl(fellPins);

        if (currentState.isFinish()) {
            states.add(addFinalFrame(fellPins));
            return this;
        }
        states.removeLast();
        states.add(currentState.bowl(pin));
        return this;
    }

    private static State addFinalFrame(String fellPins) {
        Pin pin = Pin.bowl(fellPins);
        if (pin.isStrike()) {
            return Strike.of(pin);
        }
        return Final.from(pin);
    }

    @Override
    public boolean isFinish() {
        State lastState = states.getLast();
//        if (!lastState.isFinish()) {
//            return false;
//        }
        return false;
    }

    @Override
    public List<String> getBowlResults() {
        return states.stream()
                .flatMap(state -> state.getBowlResults().stream())
                .collect(Collectors.toList());
    }

    @Override
    public State getState() {
        return this.states.getLast();
    }

}
