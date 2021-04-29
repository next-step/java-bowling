package step4.domain;

import java.util.Objects;

public class BowlingGame {
    private final Name name;
    private final Frames frames;

    public BowlingGame(Name name) {
        this.name = name;
        this.frames = new Frames();
    }

    public BowlingGame(String name) {
        this(new Name(name));
    }

    public Name name() {
        return name;
    }

    public void throwBowl(String pinCount) {
        frames.throwBowl(pinCount);
    }

    public Frames frames() {
        return frames;
    }

    public Scores scores() {
        return new Scores(frames);
    }

    public int currentFrameIndex() {
        return frames.currentIndex();
    }

    public boolean hasFinishedGame() {
        return frames.isAllFinished();
    }

    public boolean hasFinishedFrame(int index) {
        return frames.get(index).isFinished();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGame bowlingGame = (BowlingGame) o;
        return Objects.equals(name, bowlingGame.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
