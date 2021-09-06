package bowling;

public class FinalScoreFrame extends ScoreFrame {

    public FinalScoreFrame(Turn turn, FrameResult previousFrameResult) {
        super(turn, previousFrameResult);
    }

    @Override
    public ScoreFrame process(int scoreValue) {
        trial.add(new Score(scoreValue));

        if (trial.isFinalEnd()) {
            return new FinalScoreFrame(turn.getNextTurn(), FrameResult.MISS);
        }

        return this;
    }
}
