package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;

public class None implements State {

    private static final String SYMBOL_OF_NONE = " ";

    @Override
    public State bowl(Pins pins) {
        if(Gutter.isMatch(pins)){
            return Gutter.valueOf();
        }
        if(Strike.isMatch(pins)){
            return Strike.getInstance();
        }
        return Hit.valueOf(pins);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public String printResult() {
        return SYMBOL_OF_NONE;
    }
}