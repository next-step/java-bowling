package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;
import bowling.exception.BowlingStateException;

public class FirstBowl extends State {

    public FirstBowl(int pin) {
        firstPin = new Pin(pin);
    }

    @Override
    public State bowl(int secondCount) {
        if (firstPin.count() + secondCount == 10) {
            return new Spare(firstPin.count(), secondCount);
        }
        return new Miss(firstPin.count(), secondCount);
    }

    @Override
    public Score getScore() {
        throw new BowlingStateException("투구가 완료되지 않아 점수를 확인할 수 없습니다.");
    }

    @Override
    public boolean finish() {
        return false;
    }
}
