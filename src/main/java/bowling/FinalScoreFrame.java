package bowling;

public class FinalScoreFrame extends ScoreFrame {

    public FinalScoreFrame(Turn turn) {
        super(turn);
    }

    @Override
    public ScoreFrame addScore(int scoreValue) {
        frameMeta.add(new Score(scoreValue));

        if (frameMeta.isFinalEnd()) {
            return next = new FinalScoreFrame(frameMeta.getNextTurn());
        }

        return this;
    }

    @Override
    boolean isCalculable() {
        return frameMeta.isFinalEnd();
    }

    @Override
    public Score getScore(Score previousScore) {
        int total = getCount();

        return previousScore.sum(getSumScore(total));
    }
}
