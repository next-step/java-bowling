package bowling.domain.state;

import bowling.domain.KnockedPinCount;
import bowling.domain.Score;

public class Spare extends AbstractFinished {
    public Spare(KnockedPinCount pinCount, KnockedPinCount newPinCount) {
        super(pinCount, newPinCount);
    }

    public Spare(int ... pinCounts) {
        super(pinCounts);
    }

    @Override
    public State bowl(int pinCount) {
        return Bonus.ofSpare(pinCounts.knockOut(pinCount), this);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean hasBonus() {
        return true;
    }

    @Override
    public Score makeScore() {
        return Score.ofSpare();
    }

    @Override
    public Score additionalCalculate(Score beforeScore) {
        Score score = beforeScore.bowl(getValues().get(ZERO).value());
        if (score.canCalculateScore()) {
            return score;
        }
        return score.bowl(getValues().get(1).value());
    }

    @Override
    public boolean isSpare() {
        return true;
    }

    @Override
    public String display() {
        return pinCounts.getFirst() + SEPARATE_MARK + SPARE_MARK;
    }
}
