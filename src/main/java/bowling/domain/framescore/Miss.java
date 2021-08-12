package bowling.domain.framescore;

import bowling.domain.score.TurnScores;

public class Miss extends FrameScore {
    protected Miss() {
        super(TurnScores.empty());
    }

    public static Miss instance() {
        return InnerLazyClass.INSTANCE;
    }

    @Override
    public boolean isShowScoreValue() {
        return true;
    }

    @Override
    public boolean isCompleted() {
        return true;
    }

    private static class InnerLazyClass {
        private static final Miss INSTANCE = new Miss();
    }
}
