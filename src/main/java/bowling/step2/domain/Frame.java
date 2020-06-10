package bowling.step2.domain;

import java.util.ArrayList;

public class Frame {

    private final int frame;
    private final FrameScore frameScore;

    private Frame (int frame, FrameScore frameScore) {
        this.frame = frame;
        this.frameScore = frameScore;
    }

    public static Frame of (int frame, FrameScore frameScore) {
        return new Frame(frame, frameScore);
    }

    public Frame next () {
        int nextFrame = frame + 1;
        return of(nextFrame, FrameScore.of(new ArrayList<>()));
    }

    public int getValue () {
        return frame;
    }
}