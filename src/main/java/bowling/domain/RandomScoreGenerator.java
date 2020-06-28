package bowling.domain;

import bowling.domain.vo.Score;

import java.util.concurrent.ThreadLocalRandom;

public class RandomScoreGenerator implements ScoreGenerator {
    private static final int SCORE_START_BOUND = 0;
    private static final int SCORE_END_EXCLUSIVE_BOUND = 11;

    @Override
    public Score generate() {
        return new Score(ThreadLocalRandom.current()
                .nextInt(SCORE_START_BOUND, SCORE_END_EXCLUSIVE_BOUND));
    }
}
