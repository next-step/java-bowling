package bowling.domain.state;

import bowling.domain.frame.Score;
import bowling.domain.rolling.Rollings;

public class Strike extends State {
    public Strike(Rollings rollings, int remainPinCount) {
        super(rollings, remainPinCount);
    }

    @Override
    public Score calculateScore() {
        return Score.calculateStrike(rollings.calculateScore());
    }

    @Override
    public Score calculateScore(int lastFrameScore) {
        return Score.calculateStrike(lastFrameScore + rollings.calculateScore());
    }
}
