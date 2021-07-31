package bowling.domain.state;

import bowling.domain.pin.DownedPins;

public class Preparation extends State {

    private Preparation() {}
    public static Preparation instance() {
        return new Preparation();
    }

    @Override
    protected State nextState(DownedPins downedPins) {
        if (downedPins.isAllDown()) {
            return Strike.instance();
        }

        return InProgress.from(downedPins);
    }
}
