package bowling.model.state;

import bowling.model.Pins;
import bowling.model.Score;

public abstract class State {
    protected Pins pins = Pins.zero();

    protected State(){}

    protected State(Pins pins){
        this.pins = pins;
    }

    public boolean isFinished() {
        return false;
    }

    public abstract State bowling(int fallenPin);

    public abstract Score score();

    public boolean isMaxScore() {
        return pins.isMaxScore();
    }
}
