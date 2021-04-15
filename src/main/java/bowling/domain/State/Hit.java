package bowling.domain.State;

import bowling.domain.score.Score;
import bowling.domain.score.UnDefinedScore;

public class Hit implements State {

    private final PinCount firstPinCount;

    public Hit(PinCount firstPinCount) {
        this.firstPinCount = firstPinCount;
    }

    @Override
    public State newState(PinCount secondPinCount) {
        if (firstPinCount.isSpare(secondPinCount)) {
            return new Spare(firstPinCount, secondPinCount);
        }
        return new Miss(firstPinCount, secondPinCount);
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public String stateInString() {
        return firstPinCount.countInString();
    }

    @Override
    public Score score() {
        return new UnDefinedScore(firstPinCount.count());
    }

    @Override
    public Score calculatedScore(Score scoreToCalculate) {
        throw new IllegalStateException("점수를 계산 할 수 없습니다.");
    }
}
