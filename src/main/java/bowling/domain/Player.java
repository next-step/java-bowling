package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private static final int TOTAL_FRAME = 10;
    private static final int LIMIT_OF_LENGTH = 3;
    private String name;
    private Frames frames;
    private Frame frame = new Frame();

    public String getName() {
        return name;
    }

    public Player(String name) {
        checkLetter(name);
        this.name = name;
        this.frames = new Frames();
    }

    public void checkLetter(String name) {
        if (name.length() > LIMIT_OF_LENGTH) {
            throw new IllegalArgumentException("3글자까지만 입력가능합니다.");
        }
    }

    public int currentFrame() {
        return frames.currentFrame() + 1;
    }

    public void addFrame(NormalFrame normalFrame) {
        frames.addNormalFrame(normalFrame);
    }

    public void addFrame(FinalFrame finalFrame) {
        frames.addFinalFrame(finalFrame);
    }

    public void addNormalFrame(int numberOfPin) {
        frame.addFrame(numberOfPin, currentFrame());
        if (!isEndNormalFrame() && frame.isNextFrame()) {
            NormalFrame normalFrame = new NormalFrame(frame);
            addFrame(normalFrame);
            frame = new Frame();
        }
    }

    public void addFinalFrame(int numberOfPin) {
        frame.addFrame(numberOfPin, currentFrame());
        FinalFrame finalFrame = new FinalFrame(frame);
        addFrame(finalFrame);
    }

    public boolean isNextFrame() {
        return frames.isNextFrame();
    }

    public boolean isEndNormalFrame() {
        return currentFrame() == TOTAL_FRAME;
    }

    public boolean isEndFinalFrame() {
        return frames.isEndFinalFrame();
    }

    @Override
    public String toString() {
        return name;
    }

    public List<String> getSigns() {
        return frames.getNormalFrames().stream()
                .map(n -> n.getSigns())
                .collect(Collectors.toList());
    }

    public Frame getFrame() {
        return frame;
    }
}
