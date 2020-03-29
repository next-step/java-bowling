package bowling.domain.state;

import bowling.domain.frame.Score;

public class ReadyFinal implements State {

    @Override
    public State bowl(int pins) {
        if (pins == 10) {
            return new Strike(pins);
        }
        return new NextReady(pins);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public String display() {
        return null;
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
