package bowling;

public class NormalScoreFrame extends ScoreFrame {

    public NormalScoreFrame(Turn turn, FrameResult previousFrameResult) {
        super(turn, previousFrameResult);
    }

    @Override
    public ScoreFrame process(int scoreValue) {
        trial.add(new Score(scoreValue));

        if (trial.isNormalEnd()) {
            return getNextFrame(trial.getFrameResult());
        }

        return this;
    }

    private ScoreFrame getNextFrame(FrameResult frameResult) {
        if (turn.isLastNormalTurn()) {
            return new FinalScoreFrame(turn.getNextTurn(), frameResult);
        }

        return new NormalScoreFrame(turn.getNextTurn(), frameResult);
    }
}
