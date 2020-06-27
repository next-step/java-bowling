package bowling.game;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    private final LinkedList<Frame> frames;

    public Frames() {
        this.frames = new LinkedList<>();
        this.frames.add(new NormalFrame(1));
    }

    public Frame getCurrentFrame() {
        return frames.getLast();
    }

    public void createNextFrame() {
        Frame nextFrame = getCurrentFrame().createNextFrame();

        frames.add(nextFrame);
    }

    public int bowlCurrentFrame(final int pinCount) {
        Frame current = getCurrentFrame();
        int leftPin = current.bowl(pinCount);

        if (!hasRemainChance() && current instanceof NormalFrame) {
            createNextFrame();
        }

        return leftPin;
    }

    public boolean hasRemainChance() {
        return getCurrentFrame().hasRemainChance();
    }

    public boolean isEndAllFrames() {
        Frame frame = getCurrentFrame();

        return frame.isLastFrame() && !frame.hasRemainChance();
    }

    public int getCurrentFrameNumber() {
        Frame frame = getCurrentFrame();

        return frame.getFrameNumber().getNumber();
    }

    public List<String> getFramesStates() {
        return frames.stream()
                .map(Frame::getStates)
                .collect(Collectors.toList());
    }
}
