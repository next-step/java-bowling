package bowling.domain.score;

public class FinalScores extends Scores {

    private FinalScores() {
        super();
    }

    public static Scores init() {
        return new FinalScores();
    }

    @Override
    public boolean canBowl() {
        if (scores.size() <= FIRST_SCORE) {
            return true;
        }

        return isSpare();
    }

}
