package bowling.domain.state.progress;

import bowling.domain.state.BowlingPin;
import bowling.domain.state.Progress;
import bowling.domain.state.State;
import bowling.domain.state.result.Strike;

public class Ready implements Progress {

    public Ready(){
    }

    @Override
    public State bowl(BowlingPin bowlingPin) {
        if (bowlingPin.isMax()) {
            return Strike.of(bowlingPin);
        }
        return Running.of(bowlingPin);
    }
}
