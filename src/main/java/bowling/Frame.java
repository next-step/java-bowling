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
        if (isSpare()) {
            isFinished = true;
        }
        if (isFinish()) {
            return new Frame(number + 1);
        }

        return this;
    }

    public int getNumber() {
        return this.number;
    }

    private boolean isSpare() {
        return fallenPins.stream().reduce(0, (x, y) -> x + y) == 10;
    }

    private boolean isStrike(int fallenPinCount) {
        return fallenPinCount == 10;
    }

    private boolean isFinish() {
        return this.isFinished;
    }
}
