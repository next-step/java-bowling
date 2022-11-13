package bowling.domain;

public class Player {
    private final Name name;
    private final Frames frames;

    public Player(String name) {
        this.name = new Name(name);
        this.frames = new Frames();
    }

    public Player(String name, Frames frames) {
        this.name = new Name(name);
        this.frames = frames;
    }

    public boolean process(int index) {
        return !frames.end(index);
    }

    public void pitch(int index, Score score) {
        frames.addScore(index, score);
    }

    public Name getName() {
        return name;
    }

    public Frames getFrames() {
        return frames;
    }
}
