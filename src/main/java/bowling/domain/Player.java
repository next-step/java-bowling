package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private String name = "";
    private Frames frames;

    public String getName() {
        return name;
    }

    public Player(String name) {
        checkLetter(name);
        this.name = name;
        this.frames = new Frames();
    }

    public void checkLetter(String name) {
        if (name.length() > 3) {
            throw new IllegalArgumentException("3글자까지만 입력가능합니다.");
        }
    }

    public int currentFrame() {
        return frames.currentFrame() + 1;
    }

    public void addFrame(NormalFrame normalFrame) {
        frames.addNormalFrame(normalFrame);
    }

    public boolean isNextFrame() {
        return frames.isNextFrame();
    }

    public boolean isEndNormalFrame() {
        return frames.isEndNormalFrame();
    }

    public boolean isEndFinalFrame(FinalFrame finalFrame) {
        addFrame(finalFrame);
        return frames.isEndFinalFrame();
    }

    public boolean isEndFinalFrame() {
        return frames.isEndFinalFrame();
    }

    public void addFrame(FinalFrame finalFrame) {
        frames.addFinalFrame(finalFrame);
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
}
