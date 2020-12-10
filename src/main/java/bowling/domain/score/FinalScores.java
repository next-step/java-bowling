package bowling.domain.score;

public class FinalScores extends Scores {
    private final static int BONUS_SCORE = 3;

    private FinalScores(Score firstScore, Score secondScore) {
        super(firstScore, secondScore);
    }

    public static Scores init() {
        return new FinalScores(null, null);
    }

    @Override
    public boolean canBowl() {
        if(scores.size() == FIRST_SCORE) {
            return scores.get(FIRST_SCORE - 1)
                    .isStrike();
        }

        if(scores.size() == SECOND_SCORE) {
            return scores.get(FIRST_SCORE - 1)
                    .sum(scores.get(SECOND_SCORE - 1))
                    .isStrike();
        }

        return scores.size() != BONUS_SCORE;
    }

}
