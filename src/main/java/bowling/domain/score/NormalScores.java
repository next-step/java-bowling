package bowling.domain.score;

public class NormalScores extends Scores {
    private static final int SIZE = 2;

    public boolean done() {
        if (firstTurn()) {
            return false;
        }
        return scores.contains(Score.STRIKE) || scores.contains(Score.SPARE) || scores.size() == SIZE;
    }
}
