package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;
import bowling.exception.BowlingStateException;

public class Miss extends State {
    public Miss(int firstCount, int secondCount) {
        pinValidate(firstCount, secondCount);
        this.firstPin = new Pin(firstCount);
        this.secondPin = new Pin(secondCount);
        score = Score.ofMiss(firstCount, secondCount);
    }

    private void pinValidate(int firstPin, int secondPin) {
        new Pin(firstPin + secondPin);
    }

    @Override
    public Score getScore() {
        return Score.ofMiss(firstPin.count(), secondPin.count());
    }

    @Override
    public State bowl(int pin) {
        throw new BowlingStateException("다음 프레임에서 투구해주세요.");
    }

    @Override
    public boolean stateFinish() {
        return true;
    }
}
