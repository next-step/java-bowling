package bowling.domain;

public class FinalFrame extends Frame{

    private int finalScore;

    public FinalFrame(int hitNumberOfPin) {
        super(hitNumberOfPin);
    }

    public void finalBall(int hitNumberOfPin) {
        validateFinalBall();
        finalScore = hitNumberOfPin;
        score.finalScore(hitNumberOfPin);
    }

    private void validateFinalBall() {
        if (!isSpare() && !isStrike()) {
            throw new RuntimeException("마지막 공은 스트라이크 또는 스페어인 경우 가능합니다.");
        }
    }
}
