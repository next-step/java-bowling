package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;
import bowling.exception.BowlingStateException;

public class Bonus extends State {
    public Bonus(int pin, int bonusCount) {
        this.firstPin = new Pin(pin);
        score = new Score(pin, bonusCount - 1);
    }

    @Override
    public State bowl(int pin) {
        if (!stateFinish()) {
            this.secondPin = new Pin(pin);
            score = new Score(pin, MISS_BONUS_NO);
            return this;
        }
        throw new BowlingStateException("보너스 투구가 종료되었습니다.");
    }

    @Override
    public Score getScore() {
        return score;
    }

    @Override
    public boolean stateFinish() {
        return score.getBonusCount() == 0;
    }
}
