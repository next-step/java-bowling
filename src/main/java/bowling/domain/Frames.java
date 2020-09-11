package bowling.domain;

import java.util.LinkedList;

public class Frames {
    private LinkedList<Frame> finalFrame;

    public Frames(LinkedList<Frame> finalFrame) {
        this.finalFrame = finalFrame;
    }

    public static Frames getDefault(LinkedList<Frame> finalFrame) {
        return new Frames(finalFrame);
    }
}
