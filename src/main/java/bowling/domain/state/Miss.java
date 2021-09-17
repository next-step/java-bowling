package bowling.domain.state;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;

public class Miss extends Finished {
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
}
