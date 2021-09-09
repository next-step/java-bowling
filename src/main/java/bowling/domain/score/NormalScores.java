package bowling.domain.score;

public class NormalScores extends Scores {
    private static final int SIZE = 2;

    public boolean done() {
        return this.contains(ScoreType.STRIKE) || this.contains(ScoreType.SPARE) || scores.size() == SIZE;
    }
}
