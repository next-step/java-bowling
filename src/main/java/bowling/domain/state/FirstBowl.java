package bowling.domain.state;

import bowling.domain.HitCount;

public class FirstBowl extends Running {

    private int countOfPin;

    FirstBowl(int countOfPin) {
        this.countOfPin = countOfPin;
    }


    @Override
    public State bowl(HitCount hitCount) {
        if (this.countOfPin + hitCount.count() == 10) {
            return new Spare(this.countOfPin, hitCount.count());
        }

        return new Miss(this.countOfPin, hitCount.count());
    }

}
