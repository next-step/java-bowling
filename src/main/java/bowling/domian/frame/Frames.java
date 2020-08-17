package bowling.domian.frame;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private List<Frame> frames = new ArrayList<>();

    private Frames() {
        frames.add(NormalFrame.firstFrame());
    }

    public static final Frames init() {
        return new Frames();
    }


}
