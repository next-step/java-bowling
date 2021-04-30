package bowling;

public class FrameResult {
    private final String bowlingScore;

    public FrameResult(String bowlingScore) {
        this.bowlingScore = bowlingScore;
    }

    public String bowlingScore() {
        return bowlingScore;
    }
}
