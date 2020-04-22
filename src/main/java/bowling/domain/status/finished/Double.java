package bowling.domain.status.finished;

import bowling.domain.score.Score;

public class Double extends Finished {

    public static Double of() {
        return new Double();
    }

    private Double() {
        super();
    }

    @Override
    public String print() {
        return "X|X";
    }

    @Override
    public Score getScore() {
        return null;
    }
}
