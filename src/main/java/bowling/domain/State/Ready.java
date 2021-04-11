package bowling.domain.State;

import bowling.domain.frame.PinCount;

public class Ready implements State {

    private final static String SYMBOL = "";

    @Override
    public State newState(PinCount pinCount) {

        if(pinCount.isStrike()){
            return new Strike();
        }

        if(pinCount.isGutter()){
            return new Gutter();
        }

        return new Hit(pinCount);
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public String stateInString() {
        return SYMBOL;
    }
}
