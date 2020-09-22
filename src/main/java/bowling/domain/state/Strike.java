package bowling.domain.state;

import bowling.domain.frame.Score;

public class Strike extends FinishedState {

    private Strike() {
    }

    public static State of() {
        return new Strike();
    }

    @Override
    public Score calculate(Score baseScore) {
        return baseScore.add(Score.ofStrike());
    }

    @Override
    public Score getScore() {
        return Score.ofStrike();
    }

    @Override
    public boolean isFrameFinish(State state) {
        return false;
    }
}
