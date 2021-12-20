package bowling.domain.factory;

import bowling.domain.scores.FinalScores;
import bowling.domain.scores.GeneralScores;
import bowling.domain.scores.Scores;

public class BowlingScoresFactory implements ScoresFactory {

    private static final int MIN_ROUND = 0;
    private static final int MAX_ROUND = 9;

    @Override
    public Scores create(int round) {

        if (round < MIN_ROUND) {
            throw new IllegalArgumentException("볼링의 최소 라운드는 0라운드 입니다.");
        }

        if (round > MAX_ROUND) {
            throw new IllegalArgumentException("볼링의 최대 라운드는 10라운드 입니다.");
        }

        if (round == 9) {
            return new FinalScores();
        }

        return new GeneralScores();
    }
}
