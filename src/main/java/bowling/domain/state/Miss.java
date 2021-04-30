package bowling.domain.state;

import bowling.domain.HitCount;

public class Miss extends Finish {

    int countOfPin;
    int countOfPin1;

    public Miss(int countOfPin, int countOfPin1) {
        this.countOfPin = countOfPin;
        this.countOfPin1 = countOfPin1;
    }

    @Override
    public int getScore() {
        return countOfPin+countOfPin1;
    }

}
