package bowling.entity.frame;

public class NormalFrameResult implements FrameResult {
    private final String bowlingScoreResult;
    private final int totalScore;

    public NormalFrameResult(String bowlingScoreResult, int totalScore) {
        this.bowlingScoreResult = bowlingScoreResult;
        this.totalScore = totalScore;
    }

    public String bowlingScore() {
        return bowlingScoreResult;
    }

    @Override
    public int totalScore(){
        return totalScore;
    }
}
