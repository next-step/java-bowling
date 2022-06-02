package bowling.domain;

import bowling.engine.ScoreStrategy;

import java.util.ArrayList;
import java.util.List;

public class Bowling implements bowling.engine.Bowling {
    private final List<NormalFrame> normalFrames;
    private final FinalFrame finalFrame;

    public Bowling(ScoreStrategy scoreStrategy) {
        this.normalFrames = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            normalFrames.add(new NormalFrame(scoreStrategy));
        }
        this.finalFrame = new FinalFrame(scoreStrategy);
    }

    public Bowling(List<NormalFrame> normalFrames, FinalFrame finalFrame) {
        this.normalFrames = normalFrames;
        this.finalFrame = finalFrame;
    }

    public List<NormalFrame> getNormalFrames() {
        return normalFrames;
    }

    public FinalFrame getFinalFrame() {
        return finalFrame;
    }
}
