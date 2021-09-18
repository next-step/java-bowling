package bowling.domain;

import bowling.service.exception.LeftFrameBallException;
import bowling.service.exception.NotExistNextFrameException;
import bowling.service.exception.NotValidateFinalBallException;

import java.util.Objects;

public class FinalFrame extends Frame {

    private static final int NOTHING = -1;

    private int finalScore = NOTHING;

    public FinalFrame(int hitNumberOfPin) {
        super(hitNumberOfPin, Frame.LAST_FRAME);
    }

    public void finalBall(int hitNumberOfPin) {
        score.validateExistSecondScore();
        validateFinalBall();
        finalScore = hitNumberOfPin;
        score.finalBall(hitNumberOfPin);
    }

    private void validateFinalBall() {
        if (!isSpare() && !isStrike()) {
            throw new NotValidateFinalBallException("마지막 공은 스트라이크 또는 스페어인 경우 가능합니다.");
        }
    }

    @Override
    public void secondBall(int hitNumberOfPin) {
        if (!Status.STRIKE.equals(status) && score.firstScore() + hitNumberOfPin > Pin.MAX.getValue()) {
            throw new IllegalArgumentException("핀의 최고 갯수는 10개 입니다.");
        }

        score.secondBall(hitNumberOfPin);
        status = score.frameStatus();
    }

    @Override
    protected void validateFrameScore() {
        if (status == null || (!status.equals(Status.MISS) && finalScore == 0)) {
            throw new LeftFrameBallException("프레임이 아직 끝나지 않았습니다.");
        }
    }

    @Override
    public void calculateFrame() {
        NormalFrame prevFrame = (NormalFrame) this.prevFrame;

        // 이전 프레임 스페이 경우, 현재 프레임 (첫번째 공) 에서 계산
        if (prevFrame.isSpare()) {
            prevFrame.calculateFrameScore();
        }

        // 이전 프레임 스트라이크 경우
        if (prevFrame.isStrike()) {
            NormalFrame prevPrevFrame = (NormalFrame) prevFrame.prevFrame;
            // 이전이전 프레임도 스트라이크 경우, 현재 프레임 (첫번째 공) 에서 계산
            if (prevPrevFrame.isStrike()) {
                prevPrevFrame.calculateFrameScore();
            }
            // 현재프레임 (두번째 공) 에서 계산
            if (score.isExsitSecondScore()) {
                prevFrame.calculateFrameScore();
            }
        }

        // 마지막 프레임은 미스 또는 스트라이크, 스페어 경우 마지막 공 에서 계산
        if (isMiss() || (isStrike() && isExistFinalBall()) || (isSpare() && isExistFinalBall())) {
            calculateFrameScore();
        }
    }

    private boolean isExistFinalBall() {
        return finalScore != NOTHING;
    }

    @Override
    public Frame getNextFrame() {
        throw new NotExistNextFrameException("마지막 프레임의 다음 프레임은 존재하지 않습니다.");
    }

    @Override
    public int calculateFrameScore() {
        if (status == null) {
            return -1;
        }

        score.createFinalFrameTotalScore();

        return score.scoresSum();
    }

    @Override
    public int cacluateAdditionalScore(TotalScore totalScore) {
        totalScore.calculate(firstScore());

        if (totalScore.canCalucateScore()) {
            return totalScore.getScore();
        }

        totalScore.calculate(secondScore());
        return totalScore.getScore();
    }

    @Override
    public boolean isExistNextFrame() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return finalScore == that.finalScore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(finalScore);
    }
}
