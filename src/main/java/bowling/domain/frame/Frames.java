package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    public static final int BOWLING_GAME_FRAME = 9;

    private List<FrameInterface> frames = new ArrayList<>();

    public Frames() {
        for(int i = 0; i <= BOWLING_GAME_FRAME; i++) {
            frames.add(makeFrame(i));
        }
    }

    private FrameInterface makeFrame(int frameNumber) {
        if(frameNumber == BOWLING_GAME_FRAME) {
            return new FinalFrame();
        }
        return new Frame();
    }

    public void addFrameScore(int frameCount, int score) {
        frames.get(frameCount).addScore(score);
    }

    public List<FrameInterface> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
