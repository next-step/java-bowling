package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public class FinalFrame implements Frame {

    private final int index;
    private final Scores scores;

    private FinalFrame(int index) {
        this.index = index;
        this.scores = Scores.create();
    }

    public static Frame create(int index) {
        return new FinalFrame(index);
    }

    @Override
    public Frame createNext(boolean isNextLast) {
        throw new UnsupportedOperationException("마지막 프레임의 다음 프레임은 없습니다");
    }

    @Override
    public boolean canAddMoreScore() {
        return scores.canAddMore();
    }

    @Override
    public void addScore(Score score) {
        scores.add(score);
    }

    @Override
    public Scores getScores() {
        return scores;
    }

    @Override
    public int getIndex() {
        return index;
    }
}
