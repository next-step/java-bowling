package bowling;

public class NormalScoreFrame implements ScoreFrame {
    private Turn turn;
    private FrameResult previousFrameResult = FrameResult.MISS;
    private Scores scores = new Scores();

    public NormalScoreFrame(Turn turn, FrameResult previousFrameResult) {
        this.turn = turn;
        this.previousFrameResult = previousFrameResult;
    }

    public ScoreFrame bowl(int scoreValue) {
        scores.add(new Score(scoreValue));

        if (scores.isEnd()) {
            return getNextFrame(scores.getFrameResult());
        }

        return this;
    }

    private ScoreFrame getNextFrame(FrameResult frameResult) {
        if (turn.isLastDoubleBallTurn()) {
            return new FinalScoreFrame(turn.getNextTurn());
        }

        return new NormalScoreFrame(turn.getNextTurn(), frameResult);
    }
}
