package bowling.domain;

import java.util.List;

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

//    public List<Pitches> pinNumbersPerFrame() {
//        return frames.pinNumbersPerFrame();
//    }

    public void pitch(int pinNumber) {
        frames.pitch(pinNumber);
    }

    public int frameNumber() {
        return frames.frameNumber();
    }

    public boolean finished() {
        return frames.finished();
    }
}
