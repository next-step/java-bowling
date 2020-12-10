package bowling.domain.score;

import java.util.List;

public class NormalScores extends Scores {

    private NormalScores() {
        super();
    }

    public static Scores init() {
        return new NormalScores();
    }

    @Override
    public boolean canBowl() {
        if(scores.size() == SECOND_SCORE) {
            return false;
        }

        return !isStrike();
    }

    @Override
    public List<Score> getResult() {
        return scores;
    }
}