package bowling.state;

import bowling.Pin;
import bowling.Score;

public class Ready extends Running {

    @Override
    public State bowl(Pin falledPins) {
        if (falledPins.isStrike()) {
            return new Strike();
        }
        return new FirstBowl(falledPins);
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        return beforeScore;
    }

    @Override
    public String getDesc() {
        return StateSymbol.BLANK.symbol();
    }
}
