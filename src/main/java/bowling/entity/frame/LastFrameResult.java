package bowling.entity.frame;

public class LastFrameResult implements FrameResult {
    private final String bowlingScore;

    public LastFrameResult(String bowlingScore) {
        this.bowlingScore = bowlingScore;
    }

    public String bowlingScore() {
        return bowlingScore;
    }
}
