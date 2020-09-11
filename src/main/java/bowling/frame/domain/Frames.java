package bowling.frame.domain;

import java.util.List;

public class Frames {

    public static final int NOMAL_FRAME_START = 1;
    public static final int NOMAL_FRAME_END = 9;
    public static final int TOTAL_FRAME = 10;

    private List<Frame> frames;

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }
}
