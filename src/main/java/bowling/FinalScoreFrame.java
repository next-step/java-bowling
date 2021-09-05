package bowling;

public class FinalScoreFrame implements ScoreFrame {
    private Turn turn;
    private Score firstScore;
    private Score secondScore;
    private Score thirdScore;

    public FinalScoreFrame(Turn turn) {
        this.turn = turn;
    }

    @Override
    public ScoreFrame bowl(int score) {
        return null;
    }
}
