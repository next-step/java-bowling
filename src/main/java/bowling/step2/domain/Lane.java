package bowling.step2.domain;

import java.util.List;

public class Lane {
    private final String name;

    private final FrameGroup frameGroup;

    public Lane(String name, FrameGroup frameGroup) {
        this.name = name;
        this.frameGroup = frameGroup;
    }

    public static Lane of(String name) {
        return new Lane(name, FrameGroup.of());
    }

    public void pitch(int frameNo, TryNo tryNo, int count) {
        frameGroup.pitch(frameNo, tryNo, count);
    }

    public List<Frame> getScoreOfLane() {
        return frameGroup.getScoreOfFrameGroup();
    }

    public boolean isNotAbleToPitchAdditional() {
        return frameGroup.isNotAbleToPitchAdditional();
    }
}
