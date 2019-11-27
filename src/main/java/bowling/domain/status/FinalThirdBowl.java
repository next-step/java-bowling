package bowling.domain.status;

import bowling.domain.score.Score;

public class FinalThirdBowl extends FrameStatus {

    public FinalThirdBowl(int firstCountOfPin, int secondCountOfPin, int thirdCountOfPin) {
        this.firstCountOfPin = firstCountOfPin;
        this.secondCountOfPin = secondCountOfPin;
        this.thirdCountOfPin = thirdCountOfPin;
    }

    @Override
    public FrameStatus bowl(int countOfPin) {
        return null;
    }

    @Override
    public Score findScore() {
        return Score.ofMiss(thirdCountOfPin);
    }
}
