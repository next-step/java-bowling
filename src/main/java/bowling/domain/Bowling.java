package bowling.domain;

import bowling.domain.frames.Frames;

public class Bowling {

    private Name name;
    private Frames frames;

    public Bowling(final String name) {
        this.name = new Name(name);
        this.frames = new Frames();
    }

    public void roll(final String scoreText) {
        int pins = Integer.parseInt(scoreText);
        this.frames.roll(Score.valueOf(pins));
    }

    public boolean isFinish() {
        return this.frames.isFinish();
    }

    public Name getName() {
        return name;
    }

    public Frames getFrames() {
        return frames;
    }
}
