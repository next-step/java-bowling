package bowling.model.state.bonusState;

import bowling.model.Pins;
import bowling.model.state.Open;
import bowling.model.state.State;
import bowling.model.state.finishedState.FinishedState;
import bowling.model.state.finishedState.Strike;

public class BonusStart extends BonusState {

    private BonusStart(FinishedState previousState) {
        super(previousState);
    }

    public static BonusStart from(FinishedState previousState) {
        return new BonusStart(previousState);
    }

    @Override
    public State bowling(int fallenPin) {
        Pins firstScore = Pins.from(fallenPin);

        if (firstScore.isMaxScore()) {
            return Strike.from(firstScore);
        }

        return Open.from(firstScore);
    }
}
