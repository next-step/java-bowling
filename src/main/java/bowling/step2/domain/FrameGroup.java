package bowling.step2.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrameGroup {
    private final List<Frame> frameList;

    private FrameGroup(List<Frame> frameList) {
        this.frameList = frameList;
    }

    public static FrameGroup of() {
        return new FrameGroup(new ArrayList<>(Collections.singletonList(NormalFrame.of(1))));
    }

    public void pitch(int frameNo, TryNo tryNo, int count) {
        Frame frame = frameList.get(lastOfFrameList());
        frame.pitch(tryNo, count);

        if (frameNo == 10) {
            return;
        }

        if (count == 10) {
            frameList.add(frame.nextFrame());
        }

        if (tryNo == TryNo.SECOND) {
            frameList.add(frame.nextFrame());
        }
    }

    private int lastOfFrameList() {
        return frameList.size() - 1;
    }

    public List<Frame> getScoreOfFrameGroup() {
        return frameList;
    }

    public boolean isNotAbleToPitchAdditional() {
        LastFrame frame = (LastFrame) frameList.get(lastOfFrameList());
        return frame.unavailableToPitchAdditional();
    }
}
