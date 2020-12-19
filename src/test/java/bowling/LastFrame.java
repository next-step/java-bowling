package bowling;

import java.util.Optional;

class LastFrame implements Frame {
    @Override
    public Frame throwBall(int fallingPins) {
        return null;
    }

    @Override
    public Optional<Scoring> getScoring() {
        return Optional.empty();
    }

    @Override
    public int getNumber() {
        return 0;
    }

    @Override
    public int sumOfFallingPins() {
        return 0;
    }
}
