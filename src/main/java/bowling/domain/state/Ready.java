package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;
import bowling.exception.BowlingGameException;

public class Ready extends Running {
    @Override
    public State bowl(int countOfPins) {
        Pins pins = new Pins(countOfPins);
        if(pins.isStrike()) {
            return new Strike();
        }
        return new FirstBowl(pins);
    }

    @Override
    public Score calculateScore(Score beforeScore) {
        throw new BowlingGameException("레디 상태에서는 스코어 계산을 할 수 없습니다.");
    }

    @Override
    public String expression() {
        return "";
    }
}
