package bowling.model.state;

import bowling.model.CannotCalculateException;
import bowling.model.Score;

public class Ready extends Running {
    @Override
    public State bowl(int falledPins) {
        Pin currentPin = new Pin(falledPins);

        if (currentPin.isStrike()) {
            return new Strike();
        }

        return new FirstBowl(falledPins);
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
