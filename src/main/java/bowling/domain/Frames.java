package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int NOT_SCORE_STATE = -1;
    private final List<Frame> frames;

    public Frames(Frame frame) {
        this.frames = new ArrayList<>();
        add(frame);
    }

    public void add(Frame frame) {
        frames.add(frame);
    }

    public Frame getFrame(int index) {
        return frames.get(index - 1);
    }

    public FrameTotalScores calculateTotalScore(int i) {
        List<Integer> result = new ArrayList<>();
        int total = 0;
        for(int j = 1; j<=i; j++) {
            if(getFrame(j).getFrameScore().getScore() == NOT_SCORE_STATE) {
                result.add(NOT_SCORE_STATE);
                continue;
            }
            total += getFrame(j).getFrameScore().getScore();
            result.add(total);
        }
        return new FrameTotalScores(result);
    }

}
