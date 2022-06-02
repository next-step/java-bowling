package bowling.domain.state;

import bowling.domain.Score;
import bowling.exception.BowlingGameException;

public class Gutter extends Finished{
    private Score score;
    public Gutter() {
        this.score = Score.ofGutter();
    }

    @Override
    public Score getScore() {
        return this.score;
    }

    @Override
    public String expression() {
        return "-|-";
    }

    @Override
    public Score calculateAddScore(Score beforeScore) {
        throw new BowlingGameException("");
    }
}
