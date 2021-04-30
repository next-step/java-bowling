package bowling.domain.state;

import bowling.domain.HitCount;

public class Spare extends Finish {

    int countOfPin;
    int countOfPin1;

    public Spare(int countOfPin, int countOfPin1) {
        this.countOfPin = countOfPin;
        this.countOfPin1 = countOfPin1;
    }

    @Override
    public State bowl(HitCount hitCount) {
        return null;
    }

    @Override
    public int getScore() {
        return countOfPin+countOfPin1;
    }

}
