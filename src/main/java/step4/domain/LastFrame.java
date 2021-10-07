package step4.domain;

import step4.domain.state.Ready;
import step4.domain.state.State;

public class LastFrame implements Frame {
    private State state;

    public LastFrame(int i) {
        this.state = new Ready();
    }

    @Override
    public String calculateScoreFromNextFrame(Score state) {
        return null;
    }

    @Override
    public void throwBowl(int i) {

    }

    @Override
    public Frame createFrame(int i) {
        return null;
    }

    @Override
    public String getScore() {
        return null;
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public int round() {
        return 0;
    }

    @Override
    public Frame next() {
        return null;
    }

    @Override
    public String getSymbol() {
        return null;
    }
}
