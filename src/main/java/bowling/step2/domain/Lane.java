package bowling.step2.domain;

import java.util.List;

public class Lane {
    private final Participant participant;

    private final FrameGroup frameGroup;

    private Lane(String name) {
        this.participant = Participant.of(name);
        this.frameGroup = FrameGroup.of();
    }

    public static Lane of(String name) {
        return new Lane(name);
    }

    public void pitch(int count) {
        frameGroup.pitch(count);
    }

    public boolean frameFinished() {
        return frameGroup.frameFinished();
    }

    public void nextFrame() {
        frameGroup.nextFrame();
    }

    public List<Frame> current() {
        return frameGroup.current();
    }

    public Participant participant() {
        return participant;
    }
}
