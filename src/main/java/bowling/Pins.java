package bowling;

import java.util.ArrayList;
import java.util.List;

public class Pins {
    public static final int MAX_PIN_COUNT = 10;
    private final List<Integer> fallenPins = new ArrayList<>();

    public void bowl(int count) {
        fallenPins.add(count);
    }

    public boolean isStrike() {
        return fallenPins.get(0) == MAX_PIN_COUNT;
    }

    public boolean isSpare() {
        return fallenPins.get(0) + fallenPins.get(1) == MAX_PIN_COUNT;
    }

    public boolean isMiss() {
        return fallenPins.get(0) + fallenPins.get(1) < MAX_PIN_COUNT;
    }

    public boolean isFinish() {
        return fallenPins.size() == 2;
    }

    public String getDescription() {
        if (isStrike()) {
            return "X";
        }
        if (!isFinish()) {
            return stringOf(fallenPins.get(0)) + "";
        }
        if (isSpare()) {
            return stringOf(fallenPins.get(0)) + "|/";
        }
        return stringOf(fallenPins.get(0)) + "|" + stringOf(fallenPins.get(1));
    }

    private String stringOf(int pins) {
        if (pins == 0) {
            return "-";
        }
        return pins + "";
    }
}
