package bowling.domain.status;

import bowling.domain.score.Score;

public class FinalFirstBowl extends FrameStatus {

    public FinalFirstBowl(int firstCountOfPin) {
        this.firstCountOfPin = firstCountOfPin;
    }

    @Override
    public FrameStatus bowl(int countOfPin) {
        return new FinalSecondBowl(getFirstCountOfPin(), countOfPin);
    }

    @Override
    public Score findScore() {
        return Score.ofMiss(firstCountOfPin);
    }
}
