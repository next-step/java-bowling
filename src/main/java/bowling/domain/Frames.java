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

    private List<Frame> frames;
    private int step;

    public Frames(List<Frame> frames) {
        this.frames = frames;
        this.step = FIRST_STEP;
    }

    public static Frames from() {
        return new Frames(new ArrayList<Frame>() {{
            add(createFrame(BEGIN_NUMBER));
        }});
    }

    private static Frame createFrame(int number) {
        if (number < Frames.BEGIN_NUMBER) {
            throw new IllegalArgumentException(String.format("%s 보다 작은 값은 설정할 수 없습니다. [%s]", Frames.BEGIN_NUMBER, number));
        }

        if (number > Frames.END_NUMBER) {
            throw new IllegalArgumentException(String.format("%s 보다 큰 값은 설정할 수 없습니다. [%s]", Frames.END_NUMBER, number));
        }

        return NormalFrame.from(number);
    }

    private Frame next() {
        return createFrame(getTail().getNumber() + 1);
    }

    public Frame hit(int count) {
        Frame tail = getTail();

        if(tail.getNumber() == BONUS_NUMBER && tail.isClear() && step < BONUS_STEP) {
//            tail.hit(count);
            step = BONUS_STEP;

            return getTail();
        }

        int reminderCount = tail.hit(count);
        step++;

        if (reminderCount == 0 && !tail.isEndFrame()) {
            frames.add(next());
            step = FIRST_STEP;
        }

        if (step == FINAL_STEP + 1 && !tail.isEndFrame()) {
            frames.add(next());
            step = FIRST_STEP;
        }

        return getTail();
    }

    public boolean isFinish() {
        Frame tail = getTail();

        if (tail.getNumber() == BONUS_NUMBER && tail.isClear() && step < BONUS_STEP)
            return false;

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

    ////\\\\

}
