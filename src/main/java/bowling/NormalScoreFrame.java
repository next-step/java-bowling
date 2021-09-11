package bowling;

public class NormalScoreFrame extends ScoreFrame {

    public NormalScoreFrame(Turn turn) {
        super(turn);
    }

    @Override
    public ScoreFrame addScore(int scoreValue) {
        trial.add(new Score(scoreValue));

        if (trial.isNormalEnd()) {
            return getNextFrame();
        }

        return this;
    }

    private ScoreFrame getNextFrame() {
        if (turn.isLastNormalTurn()) {
            return new FinalScoreFrame(turn.getNextTurn());
        }

        return new NormalScoreFrame(turn.getNextTurn());
    }
}
