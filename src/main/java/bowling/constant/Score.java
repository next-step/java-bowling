package bowling.constant;

import bowling.domain.Hit;
import bowling.domain.frame.Frame;
import bowling.domain.pin.Pins;
import bowling.exception.NotFoundScoreException;

import java.util.stream.Stream;

public enum Score {

    STRIKE(10),
    SPARE(10),
    MISS(9),
    GUTTER(0);

    private final int hits;

    Score(int hits) {
        this.hits = hits;
    }

    public static Score of(Frame frame) {
        if (!frame.isFinish()) {
            throw new IllegalArgumentException();
        }
        return of(frame.pins());
    }

    public static Score of(Pins pins) {
        if (pins.totalHits() > Hit.MAX_NUMBER) {
            throw new NotFoundScoreException(pins.totalHits());
        }
        if (pins.totalHits() == Hit.MAX_NUMBER) {
            return strikeOrSpare(pins.firstHit());
        }
        return Stream.of(values())
                .filter(score -> score.hits == pins.totalHits())
                .findFirst()
                .orElse(MISS);
    }

    private static Score strikeOrSpare(int first) {
        if (first == Hit.MAX_NUMBER) {
            return STRIKE;
        }
        return SPARE;
    }

    public boolean isStrike() {
        return this == STRIKE;
    }

    public boolean isSpare() {
        return this == SPARE;
    }
}
