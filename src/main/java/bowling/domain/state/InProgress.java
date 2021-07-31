package bowling.domain.state;

import bowling.domain.pin.DownedPin;

public class InProgress extends State {
    public static Object from(DownedPin from) {
        return null;
    }

    @Override
    protected State nextState(DownedPin downedPin) {
        return null;
    }
}
