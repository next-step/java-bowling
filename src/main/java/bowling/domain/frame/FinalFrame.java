package bowling.domain.frame;

import bowling.domain.score.Result;
import bowling.domain.score.Score;
import bowling.domain.score.FrameScore;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FinalFrame implements Frame {

    private static final List<Result> BONUS_SCORE_RESULTS = Arrays.asList(Result.STRIKE, Result.SPARE);

    private final int index;
    private final FrameScore frameScore;

    private FinalFrame(int index) {
        this.index = index;
        this.frameScore = FrameScore.create();
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
        if (!frameScore.getFirst().isPresent() || !frameScore.getSecond().isPresent()) {
            return true;
        }

        if (!frameScore.getBonus().isPresent() && BONUS_SCORE_RESULTS.contains(frameScore.checkResult())) {
            return true;
        }

        return false;
    }

    @Override
    public void addScore(Score score) {
        frameScore.add(score);
    }

    @Override
    public FrameScore getFrameScore() {
        return frameScore;
    }

    @Override
    public Optional<Score> calculateTotalScore() {
        if (!frameScore.canCheckResult()) {
            return Optional.empty();
        }

        return Optional.ofNullable(frameScore.calculateTotalScore());
    }

    @Override
    public int getIndex() {
        return index;
    }
}
