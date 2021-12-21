package bowling.domain.factory;

import bowling.domain.scores.FinalHitScores;
import bowling.domain.scores.GeneralHitScores;
import bowling.domain.scores.HitScores;

public class BowlingHitScoresFactory implements HitScoresFactory {

    private static final int MIN_ROUND = 0;
    private static final int MAX_ROUND = 9;

    @Override
    public HitScores create(int round) {
        valid(round);

        if (isFinalRound(round)) {
            return new FinalHitScores();
        }

        return new GeneralHitScores();
    }

    @Override
    public HitScores create(int round, int hitCount) {
        valid(round);

        if (isFinalRound(round)) {
            return new FinalHitScores(hitCount);
        }

        return new GeneralHitScores(hitCount);
    }

    private boolean isFinalRound(int round) {
        return round == MAX_ROUND;
    }

    private void valid(int round) {
        if (round < MIN_ROUND) {
            throw new IllegalArgumentException("볼링의 최소 라운드는 1라운드 입니다.");
        }

        if (round > MAX_ROUND) {
            throw new IllegalArgumentException("볼링의 최대 라운드는 10라운드 입니다.");
        }
    }
}
