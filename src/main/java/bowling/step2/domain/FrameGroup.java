package bowling.step2.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrameGroup {
    private final List<Frame> frameList;

    private static final int MAX = 10;

    private FrameGroup() {
        this.frameList = new ArrayList<>(Collections.singletonList(NormalFrame.of(1)));
    }

    public static FrameGroup of() {
        return new FrameGroup();
    }

    public void pitch(int count) {
        frameList.get(frameList.size() - 1)
                .pitch(count);

    }

    public boolean frameFinished() {
        return lastFrame().finished();
    }

    public void nextFrame() {
        validateFrameGroupSize();

        frameList.add(lastFrame().nextFrame());
    }

    private void validateFrameGroupSize() {
        if (frameListSizeOverTheMax()) {
            throw new RuntimeException("최대 프레임 갯수를 초과했습니다.");
        }
    }

    private boolean frameListSizeOverTheMax() {
        return frameList.size() > MAX;
    }

    public Frame lastFrame() {
        return frameList.get(frameList.size() - 1);
    }

    public int currentSize() {
        return frameList.size();
    }

    public List<Frame> current() {
        return frameList;
    }
}
