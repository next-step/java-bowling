package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private static final int LIMIT_OF_LENGTH = 3;
    private String name;
    private Frames frames;

    public Frames getFrames() {
        return frames;
    }

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

    public boolean isEndGame() {
        return frames.isEndGame();
    }

    @Override
    public String toString() {
        return name;
    }

    public List<String> getSigns() {
        return frames.getFrames().stream()
                .map(n -> n.getSigns())
                .collect(Collectors.toList());
    }

    public void processPin(int numberOfPin) {
        frames.processPin(numberOfPin);
    }

    public Frame getFrame() {
        return frames.getFrame();
    }

    public FrameScores getFrameScore() {
        return frames.getFrameScores();
    }
}
