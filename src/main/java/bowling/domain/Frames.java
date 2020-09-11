package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    public static final int PIN_COUNT = 10;
    public static final int PIN_CLEAR_COUNT = 0;

    public static final int BEGIN_NUMBER = 1;
    public static final int END_NUMBER = 10;
    public static final int BONUS_NUMBER = END_NUMBER;

    private static final int FIRST_STEP = 1;
    private static final int FINAL_STEP = 2;
    private static final int BONUS_STEP = FINAL_STEP + 1;

    private List<Frame> normalFrames;
    private int step;

    public Frames(List<Frame> normalFrames) {
        this.normalFrames = normalFrames;
        this.step = FIRST_STEP;
    }

    public static Frames create() {
        return new Frames(new ArrayList<Frame>() {{
            add(NormalFrame.first());
        }});
    }

    public Frame hit(int count) {
        Frame tail = getTail();
        int reminderCount = tail.hit(count);
        step++;

        if (reminderCount == 0 && !tail.isEndFrame()) {
            normalFrames.add(getTail().next());
            step = FIRST_STEP;
        }

        if (step == FINAL_STEP + 1 && !tail.isEndFrame()) {
            normalFrames.add(getTail().next());
            step = FIRST_STEP;
        }

        return getTail();
    }

    public boolean isFinish() {
        Frame tail = getTail();

        if (tail.isEndFrame() && tail.isClear()) {
            return true;
        }

        if (tail.isEndFrame() && step == FINAL_STEP + 1) {
            return true;
        }

        return false;
    }

    private Frame getTail() {
        return normalFrames.get(normalFrames.size() - 1);
    }
}
