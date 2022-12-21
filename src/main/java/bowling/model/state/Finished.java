package bowling.model.state;

import bowling.model.Pin;

public class Finished implements State{

    @Override
    public State bowl(Pin pin) {
        throw new IllegalStateException("더이상 게임을 진행할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
