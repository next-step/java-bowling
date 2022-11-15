package bowling.domain;

import bowling.type.PlayStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Frames {
    public final static int LAST_FRAME_ORDER = 10;

    private final List<Frame> frames;
    private PlayStatus playStatus = PlayStatus.IN_PROGRESS;

    public Frames(Frame... frames) {
        this.frames = new ArrayList<>(Arrays.asList(frames));
    }

    public List<Frame> getFrames() {
        return frames;
    }

    private Frame getLastFrame() {
        return frames.get(frames.size() - 1);
    }

    public int getLastOrder() {
        return getLastFrame().getOrder();
    }

    public int getLastTry() {
        return getLastFrame().getLatestScore();
    }

    public void next() {
        if (isEnd()) {
            playStatus = PlayStatus.END;
            return;
        }

        if (getLastFrame().isInProgress()) {
            nextTry();
            return;
        }
        nextFrame();
    }

    public void nextTry() {
        getLastFrame().nextTry();
    }

    public void nextFrame() {
        frames.add(getLastFrame().nextFrame());
    }

    private boolean isEnd() {
        return frames.size() == LAST_FRAME_ORDER &&
                !getLastFrame().isInProgress();
    }

    public boolean isInProgress() {
        return PlayStatus.IN_PROGRESS == playStatus;
    }

    public int size() {
        return frames.size();
    }
}
