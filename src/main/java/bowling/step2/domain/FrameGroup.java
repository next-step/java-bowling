package bowling.step2.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrameGroup {
    private final List<Frame> frames;

    private static final int MAX = 10;

    public FrameGroup(List<Frame> frames) {
        this.frames = frames;
    }

    public static FrameGroup of() {
        return new FrameGroup(Collections.singletonList(NormalFrame.of(1)));
    }

    public void pitch(int count) {
        frames.get(frames.size() - 1)
                .pitch(count);
    }

    public boolean frameFinished() {
        return lastFrame().finished();
    }

    public void nextFrame() {
        validateFrameGroupSize();

        if (lastFrame() instanceof LastFrame) {
            return;
        }

        frames.add(lastFrame().nextFrame());
    }

    private void validateFrameGroupSize() {
        if (frameListSizeOverTheMax()) {
            throw new RuntimeException("최대 프레임 갯수를 초과했습니다.");
        }
    }

    private boolean frameListSizeOverTheMax() {
        return frames.size() > MAX;
    }

    public Frame lastFrame() {
        return frames.get(frames.size() - 1);
    }

    public int currentSize() {
        return frames.size();
    }

    public List<Frame> current() {
        return frames;
    }
}
