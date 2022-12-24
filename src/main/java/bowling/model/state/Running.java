package bowling.model.state;

import bowling.model.Pin;
import bowling.model.Score;

public abstract class Running implements State {
    @Override
    public abstract State bowl(Pin pin);

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Score getScore() {
        throw new IllegalStateException("점수를 생성할 수 없습니다.");
    }
}
