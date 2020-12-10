package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = Collections.unmodifiableList(frames);
    }

    public static Frames of() {
        return new Frames(creates());
    }

    private static List<Frame> creates() {
        List<Frame> frameList = new ArrayList<>();
        Frame frame = Frame.first();
        frameList.add(frame);
        
        for (int i = 0; i < 9; i++) {
            frame = frame.next();
            frameList.add(frame);
        }

        return frameList;
    }
}
