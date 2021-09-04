package bowling;

public class ScoreFrame {
    private Score firstScore;
    private Score secondScore;
    private FrameResult frameResult;

    public FrameResult tryFirstBowl(int score) {
        firstScore = new Score(score);

        return FrameResult.MISS;
    }

    public void trySecondBowl(int score) {
        secondScore = new Score(score);
    }
}
