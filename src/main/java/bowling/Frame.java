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

    public String getRecord() {
        if (isStrike(fallenPins.get(0))) {
            return "X";
        }
        if (isSpare()) {
            return getDescription(fallenPins.get(0)) + "|/";
        }
        if (isFinished) {
            return getDescription(fallenPins.get(0)) + "|" + getDescription(fallenPins.get(1));
        }
        return getDescription(fallenPins.get(0));
    }

    private boolean isSpare() {
        return fallenPins.stream().reduce(0, (x, y) -> x + y) == 10;
    }

    private boolean isStrike(int fallenPinCount) {
        return fallenPinCount == 10;
    }

    private boolean isFinish() {
        return fallenPins.size() == 2;
    }

    private String getDescription(int pins) {
        if (pins == 0) {
            return "-";
        }
        if (pins == 10) {
            return "X";
        }
        return pins + "";
    }
}
