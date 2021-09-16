package bowling.domain;

import bowling.LeftFrameBallException;

import java.util.Objects;

public class NormalFrame extends Frame {
    private Frame nextFrame;

    public NormalFrame(int hitNumberOfPin, int frameIndex) {
        super(hitNumberOfPin, frameIndex);
    }

    public static NormalFrame of() {
        return new NormalFrame(0, EMPTY_FRAME);
    }

    @Override
    public void secondBall(int hitNumberOfPin) {
        if (score.firstScore() + hitNumberOfPin > Pin.MAX.getValue()) {
            throw new IllegalArgumentException("핀의 최고 갯수는 10개 입니다.");
        }

        score.secondBall(hitNumberOfPin);
        status = score.frameStatus();
    }

    @Override
    public void validateFrameScore() {
        if (status == null) {
            throw new LeftFrameBallException("프레임이 아직 끝나지 않았습니다.");
        }
    }

    @Override
    public int calculateFrameScore() {
        validateFrameScore();
        TotalScore totalScore = score.createScore(status);

        if (totalScore.canCalucateScore()) {
            return totalScore.getScore();
        }

        return nextFrame.cacluateAdditionalScore(totalScore);
    }

    @Override
    protected int cacluateAdditionalScore(TotalScore totalScore) {
        totalScore.calculate(firstScore());

        if (totalScore.canCalucateScore()) {
            return totalScore.getScore();
        }

        if (isStrike()) {
            return nextFrame.cacluateAdditionalScore(totalScore);
        }

        totalScore.calculate(secondScore());
        return totalScore.getScore();
    }

    public Frame nextFrame(int hitNumberOfPin) {
        if (frameIndex.isNotStartFrame() && !Status.STRIKE.equals(status)) {
            score.validateExistSecondScore();
        }

        if (frameIndex.isBeforeFinalFrame()) {
            nextFrame = new FinalFrame(hitNumberOfPin);
            return nextFrame;
        }
        nextFrame = new NormalFrame(hitNumberOfPin, frameIndex.getValue() + 1);
        return nextFrame;
    }

    public Frame getNextFrame() {
        return nextFrame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame frame = (NormalFrame) o;
        return Objects.equals(score, frame.score) && status == frame.status && Objects.equals(nextFrame, frame.nextFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, status, nextFrame);
    }
}
