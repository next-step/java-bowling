package bowling.domain.status.finished;

import bowling.domain.score.Score;

public class Strike extends Finished {

    public static Strike of() {
        return new Strike();
    }

    private Strike() {
        super();
    }

    @Override
    public String print() {
        return "X";
    }

    @Override
    public Score getScore() {
        return Score.ofStrike();
    }
}
