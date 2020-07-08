package bowling.domain.frame;

import bowling.domain.score.FrameScore;
import bowling.domain.score.Result;
import bowling.domain.score.Score;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FinalFrame implements Frame {
    private static final List<Result> BONUS_SCORE_RESULTS = Arrays.asList(Result.STRIKE, Result.SPARE);

    private final int index;
    private final FrameScore frameScore;
    private Score bonusScore;

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
        return frameScore.canAddMoreScore() || canAddBonusScore();
    }

    private boolean canAddBonusScore() {
        Result result = frameScore.checkResult();
        return BONUS_SCORE_RESULTS.contains(result) && bonusScore == null;
    }

    @Override
    public void addScore(Score score) {
        if (frameScore.canAddMoreScore()) {
            frameScore.add(score);
            return;
        }

        if (bonusScore == null) {
            bonusScore = score;
        }
    }

    @Override
    public FrameScore getFrameScore() {
        return frameScore;
    }

    @Override
    public Optional<Score> getBonusScore() {
        return Optional.ofNullable(bonusScore);
    }

    @Override
    public Optional<Score> calculateTotalScore() {
        if (!frameScore.canCheckResult()) {
            return Optional.empty();
        }

        if (bonusScore == null) {
            return Optional.of(frameScore.sum());
        }

        return Optional.of(frameScore.sum().add(bonusScore));
    }

    @Override
    public int getIndex() {
        return index;
    }
}
