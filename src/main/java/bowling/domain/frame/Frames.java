package bowling.domain.frame;

import java.util.LinkedList;
import java.util.List;

public class Frames {

    private static final int TOTAL_FRAME_SIZE = 10;

    private final LinkedList<Frame> frames;

    public Frames(final List<Frame> frames) {
        this.frames = new LinkedList<>(frames);
    }

    public Frames addFrame(Frame added) {
        List<Frame> merge = new LinkedList<>(frames);
        merge.add(added);
        return new Frames(merge);
    }

    public LinkedList<Frame> getFrames() {
        return frames;
    }
}
