package bowling.domain;

import java.util.Objects;

public class FinalFrame extends Frame {

    private int finalScore;

    public FinalFrame(int hitNumberOfPin) {
        super(hitNumberOfPin, Frame.LAST_FRAME);
    }

    public void finalBall(int hitNumberOfPin) {
        validateFinalBall();
        finalScore = hitNumberOfPin;
        score.finalBall(hitNumberOfPin);
    }

    private void validateFinalBall() {
        if (!isSpare() && !isStrike()) {
            throw new RuntimeException("마지막 공은 스트라이크 또는 스페어인 경우 가능합니다.");
        }
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
