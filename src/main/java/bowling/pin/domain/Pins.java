package bowling.pin.domain;

import bowling.ball.domain.Ball;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pins {

    private static final int DEFAULT_PITCH_COUNT = 2;

    private static List<Pin> pins = new ArrayList<>();

    public Pins(List<Pin> pins) {
        this.pins = pins;
    }

    public static Pins playPitch(String point, int tryPitchNumber) {
        Pin pin = pitchResult(Ball.pitch(point, tryPitchNumber));
        pins.add(pin);
        return new Pins(pins);
    }

    public static Pin pitchResult(Ball ball) {
        int remainingPins = getRemainingPins(ball);
        if (ball.getPitchNumber() == 1 && remainingPins == 0) {
            return Pin.toStrike(ball, remainingPins);
        }

        if (ball.getPitchNumber() == 2 && remainingPins == 0) {
            return Pin.toSpare(ball, remainingPins);
        }

        if (ball.getPitchNumber() == 1 && remainingPins == Pin.initPins) {
            return Pin.toGutter(ball, remainingPins);
        }

        if (ball.getPitchNumber() == 2 && remainingPins == ball.getPoint()) {
            return Pin.toGutter(ball, remainingPins);
        }

        return Pin.toMiss(ball, remainingPins);
    }

    private static int getRemainingPins(Ball ball) {
        if (pins.size() != 0) {
            return pins.get(0).getRemainingPins() - ball.getPoint();
        }
        return Pin.initPins - ball.getPoint();
    }

    public int size() {
        return pins.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins1 = (Pins) o;
        return Objects.equals(pins, pins1.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }

    @Override
    public String toString() {
        return String.valueOf(pins);
    }
}
