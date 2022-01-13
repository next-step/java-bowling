package bowling.state.ended;

import bowling.Pins;

public class Spare extends Ended {

    private final Pins fallenPins;

    public Spare(Pins fallenPins) {
        this.fallenPins = fallenPins;
    }

    @Override
    public String symbol() {
        return fallenPins.symbol() + "|/";
    }

    @Override
    public boolean isMiss() {
        return false;
    }
}
