package bowling.domain.status;

import bowling.domain.score.Score;

public class Spare extends FrameStatus {

    public Spare(int firstCountOfPin, int secondCountOfPin) {
        this.firstCountOfPin = firstCountOfPin;
        this.secondCountOfPin = secondCountOfPin;
    }

    @Override
    public FrameStatus bowl(int countOfPin) {
        throw new IllegalArgumentException("Spare 상태에서 또 던질 수 없습니다.");
    }

    @Override
    public Score findScore() {
        return Score.ofSpare();
    }
}
