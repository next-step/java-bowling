package bowling.domain.state;

import bowling.domain.frame.Score;

public class InitialState extends State {
    public InitialState(boolean isFinal) {
        super(isFinal);
    }

    @Override
    public Score calculateScore() {
        return Score.calculateNormal(this.rollings.calculateScore());
    }

    @Override
    public Score calculateScore(int lastFrameScore) {
        return Score.calculateNormal(lastFrameScore + this.rollings.calculateScore());
    }
}
