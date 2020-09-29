package bowling.domain;

import bowling.exception.GameOverException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FinalFrame implements Frame {

    private static final int MIN_PITCH_COUNT = 2;
    private static final int MAX_PITCH_COUNT = 3;

    private final Pins pins;
    private Score score;

    public FinalFrame() {
        this.pins = new Pins();
    }

    public void pitch(int count) {
        if (this.isEnd()) {
            throw new GameOverException();
        }

        pins.pitch(count);
        createScore();
    }

    private void createScore() {
        this.score = new Score(pins.getSum(), 0);
    }

    public boolean isEnd() {
        if (pins.isEmpty()) {
            return false;
        }

        if (pins.overPitching(MIN_PITCH_COUNT) && !pins.hasEndScore()) {
            return true;
        }

        return pins.overPitching(MAX_PITCH_COUNT);
    }

    public List<Pin> getPins() {
        return Collections.unmodifiableList(pins.getPins());
    }

    @Override
    public Frame next() {
        throw new GameOverException();
    }

    @Override
    public List<String> getFallenPins() {
        return pins.getFallenPins();
    }

    @Override
    public Score getScore() {
        return this.score;
    }

    public boolean hasScore() {
        return isEnd();
    }

    @Override
    public void calculateScore(int index, int count) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(pins, that.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
