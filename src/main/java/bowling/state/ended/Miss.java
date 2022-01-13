package bowling.state.ended;

import bowling.Pins;

public class Miss extends Ended {

    private final Pins beforePins;
    private final Pins afterPins;

    public Miss(Pins beforePins, Pins afterPins) {
        this.beforePins = beforePins;
        this.afterPins = afterPins;
    }

    @Override
    public String symbol() {
        return beforePins.symbol() + "|" + afterPins.symbol();
    }
}
