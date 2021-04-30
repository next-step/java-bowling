package bowling.entity.frame;

public class NormalFrameResult implements FrameResult {
    private final String bowlingScore;

    public NormalFrameResult(String bowlingScore) {
        this.bowlingScore = bowlingScore;
    }

    public String bowlingScore() {
        return bowlingScore;
    }
}
