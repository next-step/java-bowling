package bowling.domain.state;

import bowling.domain.score.Score;

public class Gutter extends State {

    private static final int ZERO = 0;

    @Override
    public String symbol() {
        return "-";
    }

    @Override
    public int totalScore() {
        return ZERO;
    }

    @Override
    public Score calculateScore(Score before) {
        return new Score(ZERO, before);
    }

    @Override
    public State bowling(int pins) {
        return Ready.of(pins);
    }
}
