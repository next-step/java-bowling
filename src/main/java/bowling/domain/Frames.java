package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    public static final int PIN_COUNT = 10;
    public static final int PIN_CLEAR_COUNT = 0;

    public static final int BEGIN_NUMBER = 1;
    public static final int END_NUMBER = 10;
    public static final int BONUS_NUMBER = END_NUMBER;

    public static final int DEFAULT_TRY_COUNT = 2;
    public static final int BONUS_TRY_COUNT = DEFAULT_TRY_COUNT + 1;

    private List<Frame> frames;
    private int maxTryCount;
    private int tryCount;

    public Frames(List<Frame> frames) {
        this.frames = frames;
        this.maxTryCount = DEFAULT_TRY_COUNT;
        this.tryCount = 0;
    }

    public static Frames from() {
        return new Frames(new ArrayList<Frame>() {{
            add(NormalFrame.from());
        }});
    }

    private Frame next() {
        Frame nextFrame = getTail().next();

        tryCount = 0;
        maxTryCount = DEFAULT_TRY_COUNT;

        return nextFrame.isLastFrame() ? LastFrame.from() : nextFrame;
    }

    public Result hit(int count) {
        Frame tail = getTail();

        int reminderCount = tail.hit(count);
        tryCount++;

        Result result = Result.of(tail.getNumber(), tryCount, count, tail.isClear());

        if (tail.getNumber() == BONUS_NUMBER && tryCount <= maxTryCount && tail.isClear()) {
            maxTryCount = BONUS_TRY_COUNT;
            tryCount = BONUS_TRY_COUNT - 1;
            return result;
        }

        if (reminderCount == 0 && !tail.isLastFrame()) {
            frames.add(next());
        }

        if (tryCount == DEFAULT_TRY_COUNT && !tail.isLastFrame()) {
            frames.add(next());
        }

        return result;
    }

    public boolean isFinish() {
        Frame tail = getTail();

        if (tail.getNumber() == BONUS_NUMBER && tryCount <= maxTryCount && tail.isClear()) {
            return false;
        }

        if (tail.isLastFrame() && tail.isClear()) {
            return true;
        }

        if (tail.isLastFrame() && tryCount == maxTryCount) {
            return true;
        }

        return false;
    }

    private Frame getTail() {
        return frames.get(frames.size() - 1);
    }

    public int getNumber() {
        return getTail().getNumber();
    }
}
