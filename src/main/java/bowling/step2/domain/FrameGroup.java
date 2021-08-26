package bowling.step2.domain;

import java.util.ArrayList;
import java.util.List;

public class FrameGroup {
    private final List<NormalFrame> frameList;

    private FrameGroup(List<NormalFrame> frameList) {
        this.frameList = frameList;
    }

    public static FrameGroup of() {
        return new FrameGroup(new ArrayList<>());
    }
}
