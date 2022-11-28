package bowling.state;

import bowling.Pin;

public abstract class Finished implements State {

    @Override
    public State bowl(Pin falledPins) {
        throw new IllegalStateException("게임이 끝나서 더이상 진행할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
