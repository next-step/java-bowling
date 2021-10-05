package bowling.domain.state.running;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.finish.Miss;
import bowling.domain.state.finish.Spare;

public class FirstBowl extends Running {

    private final Pin first;

    public FirstBowl(Pin first) {
        this.first = first;
    }

    @Override
    public State bowl(Pin pin) {
        if (first.isSpare(pin)) {
            return new Spare(first, pin);
        }
        return new Miss(first, pin);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        return score.addPin(first);
    }

}
