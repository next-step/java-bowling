package bowling.domain.state;

import bowling.domain.frame.Score;

public class Gutter extends FinishedState {

    private Gutter() {
    }

    public static State of() {
        return new Gutter();
    }

    @Override
    public Score calculate(Score baseScore) {
        baseScore = baseScore.add(Score.ofGutter());
        if (baseScore.isPending()) {
            baseScore = baseScore.add(Score.ofGutter());
        }
        return baseScore;
    }

    @Override
    public Score getScore() {
        return Score.ofGutter();
    }
}
