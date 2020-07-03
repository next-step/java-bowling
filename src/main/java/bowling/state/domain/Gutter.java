package bowling.state.domain;

import bowling.frame.domain.Score;

public class Gutter extends Finished {

    public static State of() {
        return new Gutter();
    }

    @Override
    public Score getScore() {
        return Score.ZERO;
    }

    @Override
    public String view() {
        return "-";
    }

}
