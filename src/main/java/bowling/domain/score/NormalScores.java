package bowling.domain.score;

import java.util.List;
import java.util.Optional;

public class NormalScores extends Scores {

    private NormalScores(Score firstScore, Score secondScore) {
        super(firstScore, secondScore);
    }

    public static Scores init() {
        return new NormalScores(null, null);
    }

    @Override
    public boolean canBowl() {
        if(scores.size() == SECOND_SCORE) {
            return false;
        }

        return !Optional.ofNullable(scores.get(FIRST_SCORE))
                .map(Score::isStrike)
                .orElse(false);
    }

    @Override
    public List<Score> getResult() {
        return scores;
    }
}