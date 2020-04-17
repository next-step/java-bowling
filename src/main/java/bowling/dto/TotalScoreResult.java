package bowling.dto;

public class TotalScoreResult {

    private final int totalScore;
    private final boolean canDisplay;

    private TotalScoreResult(int totalScore, boolean canDisplay) {
        this.totalScore = totalScore;
        this.canDisplay = canDisplay;
    }

    public static TotalScoreResult newInstance(final SubTotalConsoleResult subTotalConsoleResult, final int totalScore) {
        return new TotalScoreResult(subTotalConsoleResult.sumTotalScore(totalScore), subTotalConsoleResult.canDisplay());
    }

    public int getTotalScore() {
        return totalScore;
    }

    public boolean isCanDisplay() {
        return canDisplay;
    }
}
