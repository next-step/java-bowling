package bowling;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private List<Integer> fallenPins = new ArrayList<>();
    private int number;
    private boolean isFinished = false;

    public Frame(int number) {
        this.number = number;
    }

    public Frame bowl(int fallenPinCount) {
        fallenPins.add(fallenPinCount);
        if (isStrike(fallenPinCount)) {
            isFinished = true;
        }
        if (isFinish()) {
            return new Frame(number + 1);
        }

        return this;
    }

    private boolean isStrike(int fallenPinCount) {
        return fallenPinCount == 10;
    }

    private boolean isFinish() {
        return this.isFinished;
    }

    public int getNumber() {
        return this.number;
    }
}
