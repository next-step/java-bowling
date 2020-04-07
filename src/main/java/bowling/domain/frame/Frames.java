package bowling.domain.frame;

import java.util.LinkedList;

public class Frames {
    private static final int READY_FRAME = 0;

    private LinkedList<Frame> frames;

    public Frames(LinkedList<Frame> frames) {
        this.frames = frames;
    }

    public LinkedList<Frame> getFramesByScoreCalculation() {
        LinkedList<Frame> framesBeforeCalculation = new LinkedList<>(frames);
        LinkedList<Frame> states = new LinkedList<>();

        while (framesBeforeCalculation.size() != READY_FRAME) {
            states.add(calculate(framesBeforeCalculation));
        }
        return states;
    }

    private Frame calculate(LinkedList<Frame> frames) {
        Frame first = frames.removeFirst();
        for (Frame frame : frames) {
            first.calculateByBeforeScore(frame.getState());
        }
        return first;
    }
}
