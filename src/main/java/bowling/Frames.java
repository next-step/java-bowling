package bowling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    private final List<Frame> values;

    public Frames() {
        values = new ArrayList<>();
    }

    public boolean isEnd() {
        return !values.isEmpty() && findCurrentFrame().isFinalFrame() && findCurrentFrame().isEnd();
    }

    public void bowl(int knockedOutCount) {
        findCurrentFrame().bowl(knockedOutCount);
    }

    public void prepareFrame() {
        if (values.isEmpty()) {
            values.add(NormalFrame.ofFirst());
        }

        if (findCurrentFrame().isEnd()) {
            nextFrame();
        }
    }

    private Frame findCurrentFrame() {
        return values.get(values.size() - 1);
    }

    private void nextFrame() {
        if (isBeforeFinalFrame()) {
            values.add(NormalFrame.ofFinal());
            return;
        }
        values.add(NormalFrame.ofNext(findCurrentFrame()));
    }

    private boolean isBeforeFinalFrame() {
        return findCurrentFrame().isBeforeFinalFrame();
    }

    public boolean isNotCurrentFrameEnd() {
        return !findCurrentFrame().isEnd();
    }

    public List<Frame> values() {
        return Collections.unmodifiableList(values);
    }
}
