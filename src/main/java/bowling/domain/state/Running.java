package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.exception.BowlingStateException;

public abstract class Running extends State {

    public abstract State bowl(int pin);

    @Override
    public Score getScore() {
        throw new BowlingStateException("투구가 완료되지 않아 점수를 확인할 수 없습니다.");
    }

    @Override
    public boolean stateFinish() {
        return false;
    }
}
