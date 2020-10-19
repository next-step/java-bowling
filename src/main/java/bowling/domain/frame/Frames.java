package bowling.domain.frame;

import bowling.domain.pin.Pin;

import java.util.LinkedList;

public class Frames {
    private static final int FINAL_FRAME = 8;
    private LinkedList<Frame> frames = new LinkedList<>();

    public Frames() {
        startGame();
    }

    private void startGame() {
        for (int i = 0; i <= FINAL_FRAME; i++) {
            frames.add(new NormalFrame(i));
        }
        frames.add(new FinalFrame());
    }

    public Frames(LinkedList<Frame> frames) {
        this.frames = new LinkedList<>(frames);
    }

    public void addFrame(Frame frame) {
        frames.add(frame);
    }

    public LinkedList<Frame> getFrames() {
        return frames;
    }

    public int getFrameIndex(Frame frame) {
        int frameIndex = 0;
        Frame targetFrame = frames.get(frameIndex);
        while (!frame.equals(targetFrame)) {
            frameIndex++;
            targetFrame = frames.get(frameIndex);
        }
        return frameIndex + 1;
    }

    public Frame getFrame(int index) {
        return frames.get(index);
    }

    public Frame getFirstFrame() {
        return frames.getFirst();
    }

    public Frame getNextFrame() {
        return frames.stream()
                .filter(Frame::canRoll)
                .findFirst()
                .orElseGet(() -> frames.get(FINAL_FRAME));
    }

    public void calculateScores(Pin pin) {
        this.frames.stream()
                .filter(frame -> frame.hasScore())
                .forEach(frame -> frame.calculateScore(pin.getPin()));
    }


}
