package bowling.state.running;

import bowling.Pins;
import bowling.state.Throwing;
import bowling.state.ended.Strike;

public class Ready extends Running {

    @Override
    public Throwing bowl(Pins fallenPins) {
        if (fallenPins.isStrike()) {
            return new Strike();
        }
        return new FirstBowl(fallenPins);
    }

    @Override
    public String symbol() {
        return "";
    }

    @Override
    public boolean isMiss() {
        return false;
    }
}
