package bowling.domain;

import bowling.domain.state.End;
import bowling.domain.state.Progress;
import bowling.domain.state.Start;
import bowling.domain.state.State;

import java.util.Collections;
import java.util.List;

public class NormalFrame extends TemplateFrame {

    private NormalFrame() {
        super();
    }

    private NormalFrame(FrameInfo frameInfo) {
        super(frameInfo, new Start());
    }

    public static Frame first() {
        return new NormalFrame();
    }

    @Override
    public Frame next() {
        if (frameInfo.nextLast()) {
            return FinalFrame.create(frameInfo.next());
        }
        return new NormalFrame(frameInfo.next());
    }

    @Override
    public void addPitch(Pitch pitch) {
        frameInfo.addPitch(pitch);
    }

    @Override
    public List<Pitch> pitches() {
        return Collections.unmodifiableList(frameInfo.pitches());
    }

    @Override
    public void changeState() {
        if (isStrike() || isSpare()) {
            changeState(new End());
            return;
        }
        checkProgress();
    }

    private void checkProgress() {
        if (state instanceof Progress) {
            changeState(new End());
            return;
        }
        changeState(new Progress(false));
    }

    private void changeState(State state) {
        this.state = state;
    }

    private boolean isStrike() {
        return frameInfo.isStrike();
    }

    private boolean isSpare() {
        return frameInfo.isSpare();
    }

    @Override
    public State state() {
        return state;
    }

    @Override
    public boolean isFinal() {
        return false;
    }
}
