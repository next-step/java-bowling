package bowling.entity.score;

import bowling.entity.Pin;

import java.util.Objects;

public class None implements ScoreType {
    @Override
    public String scoreResult() {
        return "0";
    }

    @Override
    public boolean isFrameEnd() {
        return false;
    }

    @Override
    public ScoreType pinResult(Pin fallenPin) {
        if (fallenPin.isStrike()) {
            return new Strike();
        }
        return new NormalScore(fallenPin);
    }

}
