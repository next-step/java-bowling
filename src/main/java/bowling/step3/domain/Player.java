package bowling.step3.domain;

import java.util.HashMap;
import java.util.Map;

public class Player {

    private final Name name;
    private int currentFrameNum;
    private final Frames frames;

    public Player(String name) {
        this.name = new Name(name);
        this.frames = new Frames();
        this.currentFrameNum = 1;
    }


    public void bowl(int count) {
        frames.add(this.currentFrameNum,count);

    }

    public boolean IsEndedFrame(int frameNum) {
        if (frameNum == this.currentFrameNum &&
                this.frames.isEndedFrame(frameNum)) {
            this.currentFrameNum++;
            return true;
        }
        return false;
    }

    public Frames frames() {
        return frames;
    }

    public String name() {
        return name.getName();
    }

}
