package bowling.domain.state;

import bowling.domain.score.Score;

public class Gutter extends State {

    @Override
    public State bowling(int pins) {
        return Ready.of(pins);
    }

    @Override
    public String symbol() {
        return "-";
    }

    @Override
    public Score score() {
        return null;
    }
}
