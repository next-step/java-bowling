package bowling.domain.set;

import bowling.domain.FrameResults;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrameSets {

    private final List<FrameSet> frameSets = new ArrayList<>();

    public static FrameSets create() {
        return new FrameSets();
    }

    private FrameSets() {
        frameSets.add(NormalFrameSet.createFirst());
    }

    public boolean hasNext() {
        return !getCurrentFrameSet().isEndedGame();
    }

    public int getCurrentPlayCount() {
        return getCurrentFrameSet().getPlayCount();
    }

    public FrameResults play(int hitCount) {
        FrameSet frameSet = getCurrentFrameSet();
        frameSet.play(hitCount);

        FrameResults frameResults = new FrameResults(Collections.unmodifiableList(frameSets));

        if (frameSet.isEndedFrame() && !frameSet.isEndedGame()) {
            FrameSet nextSet = frameSet.getNext();

            if (nextSet != null) {
                frameSets.add(frameSet.getNext());
            }
        }

        return frameResults;
    }

    public FrameResults getCurrentFrameResults() {
        return new FrameResults(Collections.unmodifiableList(frameSets));
    }

    public boolean isEnd() {
        return getCurrentFrameSet().isEndedGame();
    }

    private FrameSet getCurrentFrameSet() {
        return frameSets.get(frameSets.size() - 1);
    }
}
