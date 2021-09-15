package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;
import bowling.exception.BowlingStateException;

public class Spare extends State {
    public Spare(int firstCount, int secondCount) {
        pinValidate(firstCount, secondCount);
        this.firstPin = new Pin(firstCount);
        this.secondPin = new Pin(secondCount);
        score = Score.ofSpare();
    }

    private void pinValidate(int firstCount, int secondCount) {
        if (firstCount + secondCount != 10) {
            throw new BowlingStateException("spare는 두번 투구의 합이 10이 되어야합니다.");
        }
    }

    @Override
    public Score getScore() {
        return Score.ofSpare();
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
