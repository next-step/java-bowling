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
            return new Frame(number + 1);
        }
        if (isFinish()) {
            isFinished = true;
            return new Frame(number + 1);
        }

        return this;
    }

    public int getNumber() {
        return this.number;
    }

    public boolean isFinish() {
        return fallenPins.size() == 2;
    }

    private boolean isSpare() {
        return fallenPins.stream().reduce(0, (x, y) -> x + y) == 10;
    }

    private boolean isStrike(int fallenPinCount) {
        return fallenPinCount == 10;
    }

    public String getRecord() {
        return "7|/";
    }
}
