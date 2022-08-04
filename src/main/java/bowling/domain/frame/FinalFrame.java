package bowling.domain.frame;

import java.util.List;

public class FinalFrame extends Frame {
    public int bonusTryPossible = 1; // 보너스를 줄 수 있는 남은 횟수

    @Override
    public Frame moveToNextFrame() {
        if (remainedTryNo == 0) {
            return null;
        }
        return this;
    }

    @Override
    public void handleAfterTry(int fallenPin) {
        fallenPins.add(fallenPin);
        restOfPins -= fallenPin;
        remainedTryNo--;
        addBonusIfNeeded();
        refillPins();
    }

    @Override
    public ScoreType scoreType() {
        return ScoreType.COMMON;
    }

    private void addBonusIfNeeded() {
        if (bonusTryPossible == 1 && bonusCondition()) {
            remainedTryNo += bonusTryPossible--;
        }
    }

    private boolean bonusCondition() {
        return isStrike() || isSpare();
    }

    private boolean isSpare() {
        return restOfPins == 0 && remainedTryNo == 0;
    }

    private boolean isStrike() {
        return restOfPins == 0 && remainedTryNo == 1;
    }

    private void refillPins() {
        if (restOfPins == 0) {
            restOfPins = 10;
        }
    }

    public FinalFrame(int index) {
        super(index);
    }

    public FinalFrame(int index, int restOfPins, List<Integer> fallenPins) {
        super(index, restOfPins, fallenPins);
    }

    public FinalFrame(int index, Frame prev, Frame next) {
        super(index, prev, next);
    }

    public FinalFrame(int index, int restOfPins, List<Integer> fallenPins, Frame prev, Frame next) {
        super(index, restOfPins, fallenPins, prev, next);
    }

    public int getRemainedTryNo() {
        return remainedTryNo;
    }
}
