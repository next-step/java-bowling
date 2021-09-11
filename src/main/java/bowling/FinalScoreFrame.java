package bowling;

public class FinalScoreFrame extends ScoreFrame {

    public FinalScoreFrame(Turn turn) {
        super(turn);
    }

    @Override
    public ScoreFrame addScore(int scoreValue) {
        trial.add(new Score(scoreValue));

        if (trial.isFinalEnd()) {
            return new FinalScoreFrame(turn.getNextTurn());
        }

        return this;
    }
}
