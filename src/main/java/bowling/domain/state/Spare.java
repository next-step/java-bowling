package bowling.domain.state;

import bowling.domain.frame.Score;
import bowling.domain.rolling.Rollings;

public class Spare extends State {
    public Spare(Rollings rollings, int remainPinCount) {
        super(rollings, remainPinCount);
    }

    @Override
    public Score calculateScore() {
        return Score.calculateSpare(rollings.calculateScore());
    }

    @Override
    public Score calculateScore(int lastFrameScore) {
        return Score.calculateSpare(lastFrameScore + rollings.calculateScore());
    }
}
