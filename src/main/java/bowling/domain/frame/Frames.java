package bowling.domain.frame;


import bowling.domain.pin.Pins;
import bowling.domain.state.Ready;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Frames {
    private final LinkedList<Frame> frames;

    public Frames(final List<Frame> frames) {
        this.frames = new LinkedList<>(frames);
    }

    public static Frames init() {
        List<Frame> frames = new LinkedList<>();
        frames.add(new NormalFrame(1, new Ready()));
        return new Frames(frames);
    }

    public Frames bowl(Pins pins) {
        if (isFinish()) {
            throw new IllegalStateException("게임이 이미 종료 되었습니다.");
        }

        Frame lastFrame = Objects.requireNonNull(frames.getLast());
        if (lastFrame.isFinished()) {
            Frame currentFrame = lastFrame.next();
            frames.addLast(currentFrame.bowl(pins));
            return new Frames(frames);
        }
        Frame currentFrame = lastFrame.bowl(pins);
        frames.removeLast();
        frames.addLast(currentFrame);
        return new Frames(frames);
    }

    public boolean isFinish() {
        Frame lastFrame = frames.getLast();
        return lastFrame instanceof FinalFrame && lastFrame.isFinished();
    }

    public Frame lastFrame() {
        return frames.getLast();
    }

    public LinkedList<Frame> getFrames() {
        return frames;
    }
}
