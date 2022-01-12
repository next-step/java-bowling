package bowling.state.running;

import bowling.Pins;
import bowling.state.Throwing;
import bowling.state.ended.Miss;
import bowling.state.ended.Spare;

public class FirstBowl extends Running {

    private final Pins fallenPins;

    public FirstBowl(Pins fallenPins) {
        this.fallenPins = fallenPins;
    }

    @Override
    public Throwing bowl(Pins afterPins) {
        if (fallenPins.isSpare(afterPins)) {
            return new Spare();
        }
        return new Miss();
    }

    @Override
    public String symbol() {
        return String.valueOf(fallenPins.getFallenPins());
    }
}
