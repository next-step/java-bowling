package bowling.entity.score;

import bowling.entity.Pin;

import java.util.Objects;

public class Strike implements ScoreType {
    private static final String STRIKE_SYMBOL = "X";

    @Override
    public String scoreResult() {
        return STRIKE_SYMBOL;
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
