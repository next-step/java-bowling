package bowling.domain.score.framescore;

import bowling.domain.score.Score;
import bowling.domain.score.TurnScores;

public class FrameScore extends Score {
    public FrameScore(TurnScores turnScores) {
        super(turnScores.sum().value());
    }

    public static FrameScore empty() {
        return InnerLazyClass.EMPTY;
    }

    private static class InnerLazyClass {
        private static final FrameScore EMPTY = new FrameScore(TurnScores.empty()) {
            @Override
            public boolean isEmpty() {
                return true;
            }
        };
    }
}
