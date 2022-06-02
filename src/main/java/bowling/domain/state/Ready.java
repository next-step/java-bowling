package bowling.domain.state;

import bowling.domain.score.Score;

public class Ready extends State {

    private static final int MAX_PINS = 10;
    private static final int ZERO = 0;
    private static final int INIT = -1;

    public static State of(int pins) {
        return new Ready().bowling(pins);
    }

    @Override
    public String symbol() {
        return "";
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
        if (pins == MAX_PINS) {
            return new Strike(pins);
        }

        if (pins == INIT) {
            return new Ready();
        }

        return new FirstPitch(pins);
    }
}
