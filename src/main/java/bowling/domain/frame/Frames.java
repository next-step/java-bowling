package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private final List<Frame> frames;

    private Frames() {
        this.frames = new ArrayList<>();
    }

    public static Frames of() {
        return new Frames();
    }
}
