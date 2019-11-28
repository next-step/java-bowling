package bowling.domain.status;

import bowling.domain.score.Score;

public class FinalSecondBowl extends FrameStatus {

    private boolean isEnd;

    public FinalSecondBowl(int firstCountOfPin, int secondCountOfPin) {
        this.firstCountOfPin = firstCountOfPin;
        this.secondCountOfPin = secondCountOfPin;
        this.isEnd = checkEndCondition();
    }

    private boolean checkEndCondition() {
        return !(firstCountOfPin + secondCountOfPin == 10 || firstCountOfPin == 10 || secondCountOfPin == 10);
    }

    @Override
    public FrameStatus bowl(int countOfPin) {
        if (isEnd) {
            throw new IllegalArgumentException("더 이상 투구할 수 없습니다.");
        }
        return new FinalThirdBowl(firstCountOfPin, secondCountOfPin, countOfPin);
    }

    @Override
    public Score findScore() {
        return Score.ofMiss(secondCountOfPin);
    }

    public boolean isEnd() {
        return isEnd;
    }
}
