package bowling.domain.state;

import bowling.domain.frame.Score;

public class Ready implements State {
    private static final int MAX_FALLEN_PINS = 10;
    private static final String EMPTY_FRAME = " ";

    @Override
    public State bowl(int pins) {
        if (pins == MAX_FALLEN_PINS) {
            return new Strike();
        }
        return new NextReady(pins);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public String display() {
        return EMPTY_FRAME;
    }

    @Override
    public Score getScore() {
        return null;
    }

    @Override
    public Score calculateByBeforeScore(Score before) {
        return null;
    }

    @Override
    public void renewScore(Score score) {
    }
}
