package bowling.domain;

import java.util.Optional;

interface Frame {
    Frame throwBall(int fallingPins);

    Optional<Scoring> getScoring();

    int getNumber();

    int sumOfFallingPins();
}
