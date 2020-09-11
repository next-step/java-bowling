package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    public static final int BONUS_NUMBER = Frame.END_NUMBER;

    private static final int FIRST_STEP = 1;
    private static final int FINAL_STEP = 2;
    private static final int BONUS_STEP = FINAL_STEP + 1;

    private List<Frame> frames;
    private int step;

    public Frames(List<Frame> frames) {
        this.frames = frames;
        this.step = FIRST_STEP;
    }

    public static Frames create() {
        return new Frames(new ArrayList<Frame>() {{
            add(Frame.first());
        }});
    }

    public Frame hit(int count) {
        Frame tail = getTail();
        int reminderCount = tail.hit(count);
        step++;

        if (reminderCount == 0 && !tail.isEndFrame()) {
            frames.add(getTail().next());
            step = FIRST_STEP;
        }

        if (step == FINAL_STEP + 1 && !tail.isEndFrame()) {
            frames.add(getTail().next());
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
        return frames.get(frames.size() - 1);
    }
}
