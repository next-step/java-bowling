package bowling.domain.frame;

import bowling.domain.bonusScore.NormalBonusScore;
import bowling.domain.FrameResults;
import bowling.domain.FrameScore;
import bowling.domain.FrameScoreStatus;
import bowling.domain.exceptions.InvalidTryBowlException;
import bowling.domain.exceptions.InvalidTryNextFrameException;
import bowling.domain.frameStatus.NormalFrameStatus;

import java.util.Objects;

public class NormalFrame implements Frame {
    private static final int FINAL_FRAME_INDEX = 10;

    private final int index;
    private final NormalFrameStatus currentStatus;
    private final NormalFrame previousFrame;

    public NormalFrame(int index, NormalFrameStatus currentStatus, NormalFrame previousFrame) {
        this.index = index;
        this.currentStatus = currentStatus;
        this.previousFrame = previousFrame;
    }

    public static NormalFrame start(int numberOfHitPin) {
        return new NormalFrame(1, NormalFrameStatus.bowlFirst(numberOfHitPin), null);
    }

    public boolean isSpare() {
        return this.currentStatus.isSpare();
    }

    public boolean isStrike() {
        return this.currentStatus.isStrike();
    }

    private void validateBowl() {
        if (isCompleted()) {
            throw new InvalidTryBowlException("완료된 프레임에서 추가로 투구할 수 없습니다.");
        }
    }

    private void validateNext() {
        if (!isCompleted()) {
            throw new InvalidTryNextFrameException("현재 프레임이 완료되지 않은 상태에서 다음 프레임을 진행할 수 없습니다.");
        }
    }

    private NormalBonusScore calculateBonusScore() {
        return this.currentStatus.calculateBonusScore();
    }

    @Override
    public FrameScore calculatePreviousScore() {
        if (previousFrame == null) {
            return new FrameScore(FrameScoreStatus.COMPLETE, 0);
        }

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
    public FrameScore calculateCurrentScore() {
        Integer frameScoreValue = this.currentStatus.calculateCurrentResult().calculateScore();

        if (currentStatus.isCompleted() && (!currentStatus.isStrike() && !currentStatus.isSpare())) {
            return new FrameScore(FrameScoreStatus.COMPLETE, frameScoreValue);
        }

        return new FrameScore(FrameScoreStatus.NOT_READY, frameScoreValue);
    }

    @Override
    public Frame next(int numberOfHitPin) {
        validateNext();
        if (index + 1 == FINAL_FRAME_INDEX) {
            return FinalFrame.bowlFirst(numberOfHitPin, this);
        }
        return new NormalFrame(index + 1, NormalFrameStatus.bowlFirst(numberOfHitPin), this);
    }

    @Override
    public NormalFrame bowl(int numberOfHitPin) {
        validateBowl();
        return new NormalFrame(index, currentStatus.bowl(numberOfHitPin), previousFrame);
    }

    @Override
    public boolean isCompleted() {
        return this.currentStatus.isCompleted();
    }

    @Override
    public FrameResults calculateCurrentResults() {
        return this.currentStatus.calculateCurrentResult();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return index == that.index &&
                Objects.equals(currentStatus, that.currentStatus) &&
                Objects.equals(previousFrame, that.previousFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, currentStatus, previousFrame);
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "index=" + index +
                ", currentStatus=" + currentStatus +
                ", previousFrame=" + previousFrame +
                '}';
    }
}
