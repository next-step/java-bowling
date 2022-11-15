package bowling.step3.domain;

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
        frames.bowl(this.currentFrameNum, count);

    }

    public boolean isEndedFrame(int frameNum) {
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
