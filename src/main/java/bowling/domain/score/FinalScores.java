package bowling.domain.score;

import java.util.stream.IntStream;

public class FinalScores extends Scores {
    private final static int BONUS_SCORE = 3;

    private FinalScores() {
        super();
    }

    public static Scores init() {
        return new FinalScores();
    }

    @Override
    public boolean canBowl() {
        if(scores.size() == FIRST_SCORE) {
            return true;
        }

        return isSpare();
    }

    @Override
    public Score getScore(int bonusScoreCount) {
        if(bonusScoreCount == 2) {
            return getTotalScore();
        }

        return IntStream.range(0, bonusScoreCount)
                .mapToObj(index -> scores.get(index))
                .reduce(Score.ZERO_SCORE, Score::sum);
    }
}
