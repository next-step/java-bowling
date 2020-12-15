package bowling.model.state;

import bowling.model.Pins;

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

    public int score(){
        return pins.getScore();
    }

    public boolean isMaxScore() {
        return pins.isMaxScore();
    }
}
