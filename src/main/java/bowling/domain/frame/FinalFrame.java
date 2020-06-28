package bowling.domain.frame;

import bowling.domain.score.Result;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

import java.util.Arrays;
import java.util.List;

public class FinalFrame implements Frame {

    private static final List<Result> BONUS_SCORE_RESULTS = Arrays.asList(Result.STRIKE, Result.SPARE);

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
        if (!scores.getFirst().isPresent() || !scores.getSecond().isPresent()) {
            return true;
        }

        if (!scores.getBonus().isPresent() && BONUS_SCORE_RESULTS.contains(scores.checkResult())) {
            return true;
        }

        return false;
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
