package bowling.domain.frame;

import bowling.domain.strategy.BowlingStrategy;

public interface Frame {
    String score();

    boolean availablePitching();

    int pitch(BowlingStrategy bowlingStrategy);
}
