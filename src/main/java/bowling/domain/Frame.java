package bowling.domain;

import java.util.Optional;

public interface Frame {
    Frame throwBall(int fallingPins);

    Optional<Scoring> getScoring();

    int getNumber();

    int sumOfFallingPins();

    default boolean isFinish() {
        return false;
    }
}
