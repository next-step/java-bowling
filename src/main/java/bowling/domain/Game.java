package bowling.domain;

public class Game {
    Name name;
    Frames frames;

    public Game(String name) {
        this.name = new Name(name);
        this.frames = new Frames();
    }

    public Game(String name, Frames frames) {
        this.name = new Name(name);
        this.frames = frames;
    }

    public String getName() {
        return name.getName();
    }

    public void pitch(int pinNumber) {
        frames.pitch(pinNumber);
    }

    public int frameNumber() {
        return frames.frameNumber();
    }

    public boolean finished() {
        return frames.finished();
    }

    public String frameState(int frameNumber) {
        return frames.frameState(frameNumber);
    }
}
