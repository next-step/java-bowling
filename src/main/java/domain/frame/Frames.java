package domain.frame;

import domain.Pins;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {

    private List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = new ArrayList<>(frames);
    }

    public static Frames from(List<Frame> frames) {
        return new Frames(frames);
    }

    public void play(Pins fallenPins) {
        if (isGameOver()) {
            throw new GameOverException();
        }
        Frame bowledFrame = currentFrame().fillFrame(fallenPins);

        if (isSameFrame(bowledFrame)) {
            frames.set(lastFrameIndex(), bowledFrame);
            return;
        }
        frames.add(bowledFrame);
    }

    public boolean isGameOver() {
        return currentFrame().isGameOver();
    }

    public Frame currentFrame() {
        return frames.get(lastFrameIndex());
    }

    public FrameIndex currentFrameIndex() {
        return currentFrame().getIndex();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    private boolean isSameFrame(Frame bowledFrame) {
        return currentFrame().isSameFrame(bowledFrame);
    }

    private int lastFrameIndex() {
        return frames.size() - 1;
    }

    public FrameResults getResults() {
        return FrameResults.from(frames.stream()
                .map(Frame::getResult)
                .collect(Collectors.toList()));
    }

    public Frame getFirstFrame() {
        return frames.get(0);
    }
}