package bowling.state.domain;

import bowling.frame.domain.Score;

public class Strike extends Finished {

    public static State of() {
        return new Strike();
    }

    @Override
    public Score getScore() {
        return Score.TEN;
    }

    @Override
    public String view() {
        return "X";
    }

}
