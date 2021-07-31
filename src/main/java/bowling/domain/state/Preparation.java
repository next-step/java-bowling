package bowling.domain.state;

import bowling.domain.pin.DownedPin;

public class Preparation extends State {

    private Preparation() {}
    public static Preparation instance() {
        return new Preparation();
    }

    @Override
    protected State nextState(DownedPin downedPin) {
        return null;
    }
}
