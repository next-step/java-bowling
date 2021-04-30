package bowling.entity.score;

import bowling.entity.Pin;

import java.util.Objects;

public class Strike implements ScoreType {

    @Override
    public String scoreResult() {
        return "X";
    }

    @Override
    public boolean isFrameEnd() {
        return true;
    }

    @Override
    public ScoreType pinResult(Pin fallenPin) {
        if (fallenPin.isStrike()) {
            return new Strike();
        }
        return new NormalScore(fallenPin);
    }
}
