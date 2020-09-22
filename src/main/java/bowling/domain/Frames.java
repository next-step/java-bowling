package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames implements Frame {

    private static final int SIZE_ROUND_GAP = 1;
    private static final int FINAL_FRAME_NO = 10;

    private final List<NormalFrame> frames;

    public Frames() {
        frames = new ArrayList<>();
    }

    public int getRound() {
        return frames.size() + SIZE_ROUND_GAP;
    }

    @Override
    public void swing(int score) {
        NormalFrame lastFrame = lastFrame();
        lastFrame.swing(score);
    }

    private NormalFrame lastFrame() {

        if (frames.isEmpty()) {
            frames.add(new NormalFrame());
        }

        return frames.get(frames.size() - 1);
    }

    private void addFrame() {
//        frames.add(lastFrame().nextFrame(isFinalFrame()));
    }

    private boolean isFinalFrame() {
        return frames.size() >= FINAL_FRAME_NO;
    }

    public List<String> getSwingInfo() {

        List<String> swingInfos = new ArrayList<>();

        for (NormalFrame frame : frames) {
            // TODO 로직 작성
        }

        for (int i = frames.size(); i < FINAL_FRAME_NO; i++) {
            swingInfos.add("");
        }

        return swingInfos;
    }
}
