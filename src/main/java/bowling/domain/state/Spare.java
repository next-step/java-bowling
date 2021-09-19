package bowling.domain.state;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.exception.BowlingStateException;

public class Spare extends Finished {
    public Spare(int firstCount, int secondCount) {
        pinValidate(firstCount, secondCount);
        this.firstPin = new Pin(firstCount);
        this.secondPin = new Pin(secondCount);
        score = Score.ofSpare();
    }

    private void pinValidate(int firstCount, int secondCount) {
        if (firstCount + secondCount != MAX_PIN_NO) {
            throw new BowlingStateException("spare는 두번 투구의 합이 10이 되어야합니다.");
        }
    }

    @Override
    public Score getScore() {
        return Score.ofSpare();
    }
}
