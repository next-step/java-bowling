package bowling.domain.frame;

import bowling.domain.BonusScore;
import bowling.domain.FrameResults;
import bowling.domain.FrameScore;
import bowling.domain.FrameScoreStatus;
import bowling.domain.exceptions.InvalidTryBowlException;
import bowling.domain.exceptions.InvalidTryNextFrameException;
import bowling.domain.frameStatus.FinalFrameStatus;

import java.util.Objects;

public class FinalFrame implements Frame {
    private static int TEN = 10;

    private final int index;
    private final FinalFrameStatus finalFrameStatus;
    private final NormalFrame previousFrame;

    FinalFrame(int index, FinalFrameStatus finalFrameStatus, NormalFrame ninthFrame) {
        this.index = index;
        this.finalFrameStatus = finalFrameStatus;
        this.previousFrame = ninthFrame;
    }

    FinalFrame(NormalFrame ninthFrame, FinalFrameStatus finalFrameStatus) {
        this(TEN, finalFrameStatus, ninthFrame);
    }

    public static FinalFrame bowlFirst(int numberOfHitPin, NormalFrame ninthFrame) {
        return new FinalFrame(TEN, FinalFrameStatus.bowlFirst(numberOfHitPin), ninthFrame);
    }

    private void validateBowl() {
        if (isCompleted()) {
            throw new InvalidTryBowlException("종료된 프레임에는 투구할 수 없습니다.");
        }
    }

    private BonusScore calculateBonusScore() {
        return this.finalFrameStatus.calculateBonusScore();
    }

    @Override
    public FrameScore calculateCurrentScore() {
        Integer currentScore = this.calculateCurrentResults().calculateScore();
        if (this.finalFrameStatus.isCompleted()) {
            return new FrameScore(FrameScoreStatus.COMPLETE, currentScore);
        }
        return new FrameScore(FrameScoreStatus.NOT_READY, currentScore);
    }

    @Override
    public FrameScore calculatePreviousScore() {
        FrameScore previousFrameScore = this.previousFrame.calculateCurrentScore();
        if (this.previousFrame.isSpare()) {
            return previousFrameScore.applySpareBonus(calculateBonusScore().getFirstThrowScore());
        }
        if (this.previousFrame.isStrike()) {
            return previousFrameScore.applyStrikeBonus(calculateBonusScore());
        }
        return previousFrameScore;
    }

    @Override
    public Frame next(int numberOfHitPin) {
        throw new InvalidTryNextFrameException("10 프레임 이후 프레임은 존재하지 않습니다.");
    }

    @Override
    public FinalFrame bowl(int numberOfHitPin) {
        validateBowl();
        return new FinalFrame(TEN, this.finalFrameStatus.bowl(numberOfHitPin), previousFrame);
    }

    @Override
    public boolean isCompleted() {
        return this.finalFrameStatus.isCompleted();
    }

    @Override
    public FrameResults calculateCurrentResults() {
        return this.finalFrameStatus.calculateCurrentResult();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return index == that.index &&
                Objects.equals(finalFrameStatus, that.finalFrameStatus) &&
                Objects.equals(previousFrame, that.previousFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, finalFrameStatus, previousFrame);
    }
}
