package bowling.domain;

import bowling.domain.frames.Frames;

public class Bowling {

    private Name name;
    private Frames frames;

    public Bowling(final String name) {
        this.name = new Name(name);
        this.frames = new Frames();
    }
}
