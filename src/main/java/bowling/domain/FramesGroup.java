package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FramesGroup {

    private final List<NormalFrame> frames;

    private FramesGroup(List<NormalFrame> frames) {
        this.frames = frames;
    }

    public static FramesGroup initiate() {
        List<NormalFrame> frames = new ArrayList<>();
        frames.add(new NormalFrame(new PitchesGroup()));
        return new FramesGroup(frames);
    }

    public List<NormalFrame> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
