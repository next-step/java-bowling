package step4.domain.state;

import java.util.ArrayList;
import java.util.List;

public class FinalState {
    private List<State> states;

    public FinalState() {
        this.states = new ArrayList<>();
    }

    public void throwBowl(int falledPins) {
        if (falledPins == 10) {
            states.add(new Strike());
            return;
        }
    }
}
