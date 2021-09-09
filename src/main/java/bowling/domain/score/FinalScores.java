package bowling.domain.score;

public class FinalScores extends Scores {
    private static final int MIN_SIZE = 2;
    private static final int MAX_SIZE = 3;

    public boolean done() {
        if (firstTurn()) {
            return false;
        }
        if (this.contains(ScoreType.STRIKE) || this.contains(ScoreType.SPARE)) {
            return scores.size() == MAX_SIZE;
        }
        return scores.size() == MIN_SIZE;
    }
}
