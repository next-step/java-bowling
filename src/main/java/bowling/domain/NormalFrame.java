package bowling.domain;

import bowling.domain.state.End;
import bowling.domain.state.Start;
import bowling.domain.state.State;
import bowling.strategy.PitchNumberStrategy;

public class NormalFrame implements Frame {
    private final FrameInfo frameInfo;
    private State state;

    private NormalFrame() {
        this.frameInfo = FrameInfo.init();
        this.state = new Start();
    }

    private NormalFrame(FrameInfo frameInfo) {
        this.frameInfo = frameInfo;
    }

    public static Frame first() {
        return new NormalFrame();
    }

    @Override
    public void run(PitchNumberStrategy numberStrategy) {
        Pitch pitch = Pitch.first();
        while (progressing()) {
            int fallDownCount = numberStrategy.generate(pitch.pinsSize());
            Pins fallDownPins = Pins.create(fallDownCount);
            Pins pins = pitch.run(fallDownPins);
            state.pitch(pins, fallDownPins, this);
            pitch = pitch.next();
        }
    }

    @Override
    public Frame next() {
        if (frameInfo.nextLast()) {
            return FinalFrame.create(frameInfo.next());
        }
        return new NormalFrame(frameInfo.next());
    }

    @Override
    public FrameInfo info() {
        return frameInfo;
    }

    @Override
    public void changeState(State state) {
        this.state = state;
    }

    private boolean progressing() {
        return !(state instanceof End);
    }
}
