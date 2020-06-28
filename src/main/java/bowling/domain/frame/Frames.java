package bowling.domain.frame;

import java.util.LinkedList;

public class Frames {

    private final LinkedList<Frame> frames;

    private Frames() {
        this.frames = new LinkedList<>();
    }

    public static Frames create() {
        return new Frames();
    }
}
