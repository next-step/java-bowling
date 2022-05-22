package bowling.domain;

public class Game {

    private String ownerName;
    private Frames frames;

    public Game(String ownerName) {
        this.ownerName = ownerName;
        this.frames = new Frames();
    }

    public Frames frames() {
        return frames;
    }

    public Frame firstFrame() {
        return frames.head();
    }

    public String name() {
        return this.ownerName;
    }
}
