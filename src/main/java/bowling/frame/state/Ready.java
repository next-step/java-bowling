package bowling.frame.state;

import bowling.frame.CannotCalculateException;
import bowling.frame.Score;

class Ready extends Running {
    @Override
    public State bowl(int falledPins) {
        Pins pins = Pins.bowl(falledPins);
        if (pins.isStrike()) {
            return new Strike();
        }
        return new FirstBowl(pins);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        throw new CannotCalculateException();
    }

    @Override
    public String getDesc() {
        return "";
    }
}
