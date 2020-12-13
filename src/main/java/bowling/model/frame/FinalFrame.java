package bowling.model.frame;

import bowling.model.state.BonusOpen;
import bowling.model.state.State;
import bowling.model.state.finishedState.FinishedState;
import bowling.model.state.finishedState.Miss;
import bowling.model.state.finishedState.Spare;
import bowling.model.state.finishedState.Strike;

import java.util.Optional;

public class FinalFrame extends Frame {

    private Optional<State> previousState = Optional.empty();
    private int countOfTry = 0;

    public FinalFrame() {
        frameNumber = FrameNumber.from(FrameNumber.MAX_FRAME_NUMBER);
    }

    @Override
    public Frame bowling(int fallenPins) {
        countOfTry++;
        state = state.bowling(fallenPins);

        if (isAbleToBonusFrame()) {
            changeBonusFrame();
            return this;
        }

        return this;
    }
    private boolean isAbleToBonusFrame(){
        return (countOfTry == 1 && state instanceof Strike) ||
                (countOfTry == 2 && state instanceof Spare);
    }

    private void changeBonusFrame(){
        previousState = Optional.of(state);
        state = BonusOpen.from((FinishedState) state);
    }

    @Override
    public String toString() {
        if(previousState.isPresent() && state instanceof Strike){
            return previousState.get().toString() + FinishedState.DELIMITER + Strike.EXPRESSION;
        }

        if(previousState.isPresent() && !(state instanceof BonusOpen)){
            return previousState.get().toString() + FinishedState.DELIMITER + Miss.EXPRESSION;
        }

        return state.toString();
    }
}
