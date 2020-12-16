package bowling.model.state.bonusState;

import bowling.model.Pins;
import bowling.model.state.State;
import bowling.model.state.finishedState.*;

public class BonusOpen extends BonusState {

    private BonusOpen(FinishedState previousState) {
        super(previousState);
    }

    public static BonusOpen from(FinishedState previousState) {
        return new BonusOpen(previousState);
    }

    @Override
    public State bowling(int fallenPin) {
        Pins firstScore = Pins.from(fallenPin);

        if (firstScore.isMaxScore()) {
            return Strike.from(firstScore);
        }

        return Miss.of(Pins.zero(),firstScore);
    }
}
