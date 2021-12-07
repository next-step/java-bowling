package bowling.domain;

import java.util.List;

public class Game {
    Frames frames;
    Name name;

    public Game(String name) {
        this.frames = new Frames();
        this.name = new Name(name);
    }

    public String getName() {
        return name.getName();
    }

    public List<PinNumbers> pinNumbersPerFrame() {
        return frames.pinNumbersPerFrame();
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
}
