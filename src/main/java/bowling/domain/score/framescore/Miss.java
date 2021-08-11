package bowling.domain.score.framescore;

import bowling.domain.score.TurnScores;

public class Miss extends FrameScore {
    private Miss() {
        super(TurnScores.empty());
    }

    public static Miss instance() {
        return InnerLazyClass.instance;
    }

    private static class InnerLazyClass {
        private static Miss instance = new Miss();
    }
}
