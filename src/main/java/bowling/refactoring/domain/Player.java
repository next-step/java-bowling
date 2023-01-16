package bowling.refactoring.domain;


import bowling.refactoring.domain.frame.*;

public class Player {

    private final Name name;
    private final Frames frames;

    public Player(String name) {
        this.name = new Name(name);
        this.frames = new Frames();
    }

    public void bowl(int fallenPinCount)  {
        frames.bowl(fallenPinCount);
    }

    public boolean isEndedFrame(int frameNum){
        return frames.isEndedFrame(frameNum);
    }

    public Frames frames() {
        return frames;
    }

    public String name() {
        return name.getName();
    }
}
