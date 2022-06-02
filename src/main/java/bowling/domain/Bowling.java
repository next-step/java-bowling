package bowling.domain;

import bowling.engine.ScoreStrategy;

import java.util.ArrayList;
import java.util.List;

public class Bowling implements bowling.engine.Bowling {
    private final List<NormalFrame> normalFrames;
    private final FinalFrame finalFrame;

    private Bowling(ScoreStrategy scoreStrategy) {
        this.normalFrames = new ArrayList<>();
        NormalFrame normalFrame = NormalFrame.firstWithRandom(scoreStrategy);
        normalFrames.add(normalFrame);
        for (int i = 1; i < 9; i++) {
            normalFrame = (normalFrame.nextWithRandom(scoreStrategy));
            normalFrames.add(normalFrame);
        }
        this.finalFrame = new FinalFrame(scoreStrategy);
    }

    public static Bowling of(ScoreStrategy scoreStrategy) {
        return new Bowling(scoreStrategy);
    }

    public List<NormalFrame> getNormalFrames() {
        return normalFrames;
    }

    public FinalFrame getFinalFrame() {
        return finalFrame;
    }
}
