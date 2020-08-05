package bowling.domain.state;

import bowling.domain.frame.Score;
import bowling.domain.rolling.Rollings;

public class Miss extends State {
    public Miss(Rollings rollings, int remainPinCount) {
        super(rollings, remainPinCount);
    }

    @Override
    public Score calculateScore() {
        return Score.calculateNormal(rollings.calculateScore());
    }

    @Override
    public Score calculateScore(int lastFrameScore) {
        return Score.calculateNormal(lastFrameScore + rollings.calculateScore());
    }
}
