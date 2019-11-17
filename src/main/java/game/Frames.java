package game;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final String FRAMES_OVER_TEN = "프레임은 10번을 넘을 수 없습니다";
    public static final int FINAL_FRAME = 10;
    private List<Frame> frames;

    public Frames() {
        this.frames = new ArrayList<>();
    }

    public void addFrame(Frame frame) {
        if (this.frames.size() >= FINAL_FRAME) {
            throw new IllegalArgumentException(FRAMES_OVER_TEN);
        }
        this.frames.add(frame);
    }

    public List<Frame> getFrames() {
        return this.frames;
    }
}
