package bowling.domain;

import bowling.domain.vo.BowlingBalls;

import java.util.concurrent.ThreadLocalRandom;

public class RandomGameExecutor implements GameExecutor {
    private static final int SCORE_START_BOUND = 0;

    @Override
    public BowlingBalls execute(final BowlingBalls remainBalls) {
        return new BowlingBalls(ThreadLocalRandom.current()
                .nextInt(SCORE_START_BOUND, remainBalls.getNumber() + 1));
    }
}
