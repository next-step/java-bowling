package bowling;

import java.util.ArrayList;
import java.util.List;

public class Score {

    public static final int FIRST_ROUND = 1;
    public static final int SECOND_ROUND = 2;

    private final List<Pin> pins = new ArrayList<>();

    public void add(Pin falledPins) {
        pins.add(falledPins);
    }

    public int size() {
        return pins.size();
    }

    public boolean isStrike() {
        return pins.get(0).isMax();
    }

    public boolean isSpare() {
        if (!isFinished()) {
            return false;
        }
        return pins.get(1).isMax();
    }

    public boolean isGutter(int round) {
        return pins.get(round - 1).isGutter();
    }

    public boolean isMiss() {
        if (!isFinished()) {
            return false;
        }
        return pins.get(1).isMiss();
    }

    public boolean isFinished() {
        return pins.size() == SECOND_ROUND;
    }

    public int getScore(int index) {
        return pins.get(index).getFalledPins();
    }

    public List<Pin> getTotalScore() {
        return pins;
    }
}
