package bowling.domain.state;

import bowling.domain.score.Score;

public class Ready extends State {

    private static final int MAX_PINS = 10;

    private Ready() {
    }

    public static State of(int pins) {
        return new Ready().bowling(pins);
    }

    @Override
    public State bowling(int pins) {
        if (pins == MAX_PINS) {
            return new Strike(pins);
        }

        return new FirstPitch(pins);
    }

    @Override
    public String symbol() {
        return "";
    }

    @Override
    public Score score() {
        return null;
    }
}
