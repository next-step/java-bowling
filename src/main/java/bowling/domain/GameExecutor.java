package bowling.domain;

import bowling.domain.vo.BowlingBalls;

public interface GameExecutor {
    BowlingBalls execute(final BowlingBalls remainBalls);
}
