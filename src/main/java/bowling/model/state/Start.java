package bowling.model.state;

import bowling.model.Pins;
import bowling.model.state.finishedState.Strike;

public class Start extends State {
    private static final String EXPRESSION = " ";

    @Override
    public State bowling(int fallenPin) {
        Pins firstScore = Pins.from(fallenPin);

        if (firstScore.isMaxScore()) {
            return Strike.from(firstScore);
        }

        return Open.from(firstScore);
    }

    @Override
    public String toString() {
        return EXPRESSION;
    }
}
