package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    public static final int BOWLING_GAME_FRAME = 9;

    private List<Frame> frames = new ArrayList<>();

    public Frames() {
        for(int i = 0; i <= BOWLING_GAME_FRAME; i++) {
            frames.add(makeFrame(i));
        }
    }

    private Frame makeFrame(int frameNumber) {
        if(frameNumber == BOWLING_GAME_FRAME) {
            return new FinalFrame();
        }
        return new NormalFrame();
    }

    public Frame addFrameScore(int frameCount, int score) {
        frames.get(frameCount).addScore(score);
        return frames.get(frameCount);
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
