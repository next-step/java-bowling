package bowling.domain.state;

import bowling.domain.Score;
import bowling.exception.BowlingStateException;

public class Ready extends State {
    @Override
    public State bowl(int pin) {
        if(pin == 10){
            return new Strike();
        }
        return new FirstBowl(pin);
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
