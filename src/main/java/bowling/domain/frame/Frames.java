package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.status.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    private final List<Frame> frames;

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames init() {
        return new Frames(List.of(new NormalFrame(1)));
    }

    public List<Status> getFrameStatus() {
        return frames.stream()
                .map(Frame::getStatus)
                .collect(Collectors.toList());
    }

    public int getCurrentFrameIdx() {
        return frames.size() - 1;
    }

    public int getCurrentFrameNumber() {
        return frames.size();
    }

    public Frame bowl(Pin pin) {
        return getCurrentFrame().bowl(pin);
    }

    public Frames addNextFrame(Frame frame) {
        List<Frame> newFrames = new ArrayList<>(this.frames);
        if (!frame.isFinalFrame() && frame.isFinished()) {
            newFrames.add(frame.getNextFrame());
        }
        return new Frames(newFrames);
    }

    public Frame getCurrentFrame() {
        return frames.get(getCurrentFrameIdx());
    }

    public boolean isOver() {
        return getCurrentFrame().isFinalFrame() && getCurrentFrame().isFinished();
    }

    public List<Integer> getFrameScores() {
        return frames.stream()
                .filter(frame -> !frame.isFinalFrame())
                .map(frame -> frame.getScore().getScore())
                .collect(Collectors.toList());
    }
}
