package bowling2.domain.frame;

import java.util.List;

public class FinalFrame extends Frame {
    private int remainedTryNo = 2;
    public int bonusTryPossible = 1; // 보너스를 줄 수 있는 남은 횟수

    // TODO(jack.comeback) : 마지막 프레임한테 현재 프레임 물어볼 때 로직 구현
    @Override
    public Frame askCurrentFrame() {
        return null;
    }

    @Override
    public void handleAfterTry(int fallenPin) {
        fallenPins.add(fallenPin);
        restOfPins -= fallenPin;
        remainedTryNo--;
        addBonusIfNeeded();
        refillPins();
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

    public FinalFrame() {
    }

    public FinalFrame(int index) {
        super(index);
    }

    public FinalFrame(int restOfPins, List<Integer> fallenPins) {
        super(restOfPins, fallenPins);
    }

    public FinalFrame(int index, int restOfPins, List<Integer> fallenPins) {
        super(index, restOfPins, fallenPins);
    }

    public FinalFrame(int index, Frame prev, Frame next) {
        super(index, prev, next);
    }

    public int getRemainedTryNo() {
        return remainedTryNo;
    }
}
