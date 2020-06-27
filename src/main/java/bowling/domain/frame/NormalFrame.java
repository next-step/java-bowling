package bowling.domain.frame;

import bowling.domain.score.Scores;

public class NormalFrame implements Frame {

    private final int index;
    private final Scores scores;

    private NormalFrame(int index) {
        this.index = index;
        this.scores = Scores.create();
    }

    public static NormalFrame createFirst() {
        return new NormalFrame(0);
    }

    @Override
    public Frame createNext(boolean isNextFinal) {
        int nextIndex = index + 1;
        return isNextFinal ? FinalFrame.create(nextIndex) : new NormalFrame(nextIndex);
    }

    @Override
    public boolean canAddMoreScore() {
        return scores.canAddMore();
    }
}
