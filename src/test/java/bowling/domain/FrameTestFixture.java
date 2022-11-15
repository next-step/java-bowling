package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

public class FrameTestFixture {

    private FrameTestFixture() {
    }

    public static Frame createNormalBowlingGameFrame() {
        return new NormalFrame(new FinalFrame());
    }

    public static Frame createNormalBowlingGameFrame(int hit) {
        Frame frame = new NormalFrame(new FinalFrame());
        frame.add(hit);
        return frame;
    }

    public static Frame createNormalBowlingGameFrame(int hit1, int hit2) {
        Frame frame = new NormalFrame(new FinalFrame());
        frame.add(hit1);
        frame.add(hit2);
        return frame;
    }

    public static Frame createFinalBowlingGameFrame(int hit) {
        Frame frame = new FinalFrame();
        frame.add(hit);
        return frame;
    }

    public static Frame createFinalBowlingGameFrame(int hit1, int hit2) {
        Frame frame = new FinalFrame();
        frame.add(hit1);
        frame.add(hit2);
        return frame;
    }

    public static Frame createFinalBowlingGameFrame(int hit1, int hit2, int hit3) {
        Frame frame = new FinalFrame();
        frame.add(hit1);
        frame.add(hit2);
        frame.add(hit3);
        return frame;
    }

}
