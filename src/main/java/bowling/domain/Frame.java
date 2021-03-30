package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    private int number;

    private List<Integer> pintCounts = new ArrayList<>();

    private int accumulatedPintCounts;

    public Frame(int number) {
        this.number = number;
    }

    public static Frame first() {
        return new Frame(1);
    }

    public void addPinCounts(int pintCount) {
        pintCounts.add(pintCount);
        accumulatedPintCounts +=pintCount;
    }

    public boolean isDone() {
        if (number != 10) {
            if (accumulatedPintCounts == 10 || pintCounts.size() == 2) {
                return true;
            }
        } else {
            if (pintCounts.size() == 3) {
                return true;
            }
            if (pintCounts.size() == 2 && accumulatedPintCounts < 10) {
                return true;
            }
        }
        return false;
    }

    public Frame next() {
        return new Frame(number+1);
    }

    public int number() {
        return number;
    }

    public List<Integer> pinCounts() {
        return pintCounts;
    }
}
