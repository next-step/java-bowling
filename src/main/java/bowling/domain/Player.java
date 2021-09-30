package bowling.domain;

import bowling.domain.frames.Frames;

import java.util.Objects;

public class Player {

    private Name name;
    private Frames frames;

    public Player(final String name) {
        this(Name.of(name), new Frames());
    }

    public Player(final Name name, final Frames frames) {
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

    public int round() {
        return this.frames.round();
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
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(frames, player.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, frames);
    }
}
