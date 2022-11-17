package bowling.step4.domain;

import bowling.step4.domain.frame.Frames;

public class Player {

    private final Name name;
    private final Frames frames;

    public Player(String name) {
        this.name = new Name(name);
        this.frames = new Frames();
    }

    public void bowl(int frameNum, int count) {
        frames.bowl(frameNum, count);

    }

    public boolean isEndedFrame(int frameNum) {
        return this.frames.isEndedFrame(frameNum);
    }

    public Frames frames() {
        return frames;
    }

    public String name() {
        return name.getName();
    }

}
