package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    private static final int FRAME_SIZE = 10;

    private List<Frame> contents;

    private Frames(List<Frame> contents) {
        this.contents = contents;
    }

    public static Frames create() {
        List<Frame> frames = new ArrayList<>();
        frames.add(NormalFrame.createFirst());

        for (int i = 1; i < FRAME_SIZE; i++) {
            Frame frame = frames.get(i - 1);
            frames.add(frame.createNext(i == FRAME_SIZE - 1));
        }

        return new Frames(frames);
    }

    public List<Frame> getContent() {
        return Collections.unmodifiableList(contents);
    }
}
