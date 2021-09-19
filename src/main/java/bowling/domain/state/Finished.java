package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.exception.BowlingStateException;

public abstract class Finished extends State {
    public abstract Score getScore();

    @Override
    public State bowl(int pin) {
        throw new BowlingStateException("다음 프레임에서 투구해주세요.");
    }

    @Override
    public boolean stateFinish() {
        return true;
    }
}
