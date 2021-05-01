package bowling.entity.score;

import bowling.entity.Pin;

import java.util.Objects;

import static bowling.entity.Pin.MAX_PIN_COUNT;

public class None implements ScoreType {
    @Override
    public String scoreResult() {
        return "";
    }

    @Override
    public boolean isFrameEnd() {
        return false;
    }

    @Override
    public ScoreType bowl(Pin fallenPin) {
        if (fallenPin.pin() == MAX_PIN_COUNT) {
            return new Strike();
        }
        return new NormalScore(fallenPin);
    }

}
