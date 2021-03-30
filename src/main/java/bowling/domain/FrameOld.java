package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class FrameOld {

    private int number;

    private List<Integer> pintCounts = new ArrayList<>();

    private int accumulatedPintCounts;

    public FrameOld(int number) {
        this.number = number;
    }

    public static FrameOld first() {
        return new FrameOld(1);
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

    public FrameOld next() {
        return new FrameOld(number+1);
    }

    public int number() {
        return number;
    }

    public List<Integer> pinCounts() {
        return pintCounts;
    }
}
