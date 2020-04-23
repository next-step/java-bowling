package bowling.domain.status.finished;

import bowling.domain.score.Score;

public class Gutter extends Finished {

    public static Gutter of() {
        return new Gutter();
    }

    private Gutter() {
        super();
    }

    @Override
    public String print() {
        return "-|-";
    }

    @Override
    public Score getScore() {
        return Score.ofGutter();
    }
}
