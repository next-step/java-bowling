package bowling.model.state;

import bowling.model.Pin;

public class Strike extends Finished{
    private final Pin firstPins;

    Strike() {
        this.firstPins = new Pin(10);
    }
}
