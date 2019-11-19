package game;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    public static final int FINAL_FRAME = 10;
    public static final String FRAMES_OVER_TEN = "프레임은 10번을 넘을 수 없습니다";
    private List<Frame> frames;

    public Frames() {
        this.frames = new ArrayList<>();
    }

    public void addFrame(Frame frame) {
        if (this.frames.size() >= FINAL_FRAME) {
            throw new IllegalArgumentException(FRAMES_OVER_TEN);
        }

        if (frames.size() != 0) {
            this.frames.get(frames.size()-1).setNextFrame(frame);
        }

        this.frames.add(frame);
    }

    public List<Frame> getFrames() {
        return this.frames;
    }
}
