package bowling.domain;

import bowling.domain.frames.Frames;

import java.util.Objects;

public class Bowling {

    private Name name;
    private Frames frames;

    public Bowling(final String name) {
        this(Name.of(name), new Frames());
    }

    public Bowling(final Name name, final Frames frames) {
        this.name = name;
        this.frames = frames;
    }

    public void roll(final String scoreText) {
        int pins = Integer.parseInt(scoreText);
        this.frames.roll(Score.valueOf(pins));
    }

    public boolean isFinish() {
        return this.frames.isFinish();
    }

    public int lastFinishedFrameIndex() {
        return this.frames.lastFinishedFrameIndex();
    }

    public Name getName() {
        return Name.of(name);
    }

    public Frames getFrames() {
        return new Frames(frames.elements());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bowling bowling = (Bowling) o;
        return Objects.equals(name, bowling.name) && Objects.equals(frames, bowling.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, frames);
    }
}
