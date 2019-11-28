package bowling.domain.status;

import bowling.domain.score.Score;

public class FirstBowl extends FrameStatus {

    FirstBowl(int countOfPin) {
        this.firstCountOfPin = countOfPin;
    }

    @Override
    public FrameStatus bowl(int countOfPin) {
        if (this.firstCountOfPin + countOfPin == 10) {
            return new Spare(this.firstCountOfPin, countOfPin);
        }

        return new Miss(this.firstCountOfPin, countOfPin);
    }

    @Override
    public Score findScore() {
        return new Score(firstCountOfPin,0);
    }
}
