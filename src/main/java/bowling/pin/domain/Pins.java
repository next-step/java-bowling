package bowling.pin.domain;

import bowling.ball.domain.Ball;
import bowling.state.domain.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Pins {

    private static final int DEFAULT_PITCH_COUNT = 2;

    public static List<Pin> pins = new ArrayList<>();

    public Pins(List<Pin> pins) {
        this.pins = pins;
    }

    public static Pins playPitch(String point, int tryPitchNumber) {
        if (pins.size() == 2) {
            pins.clear();
        }
        Pin pin = pitchResult(Ball.pitch(point, tryPitchNumber));
        pins.add(pin);
        return new Pins(pins);
    }

    private static Pin pitchResult(Ball ball) {
        int remainingPins = getRemainingPins(ball);
        if (ball.getPitchNumber() == 1 && remainingPins == 0) { // 1회차 스트라이크
            return Pin.toStrike(ball, remainingPins);
        }

        if (ball.getPitchNumber() == 2 && remainingPins == 0) { // 2회차 스페어
            if (pins.get(0).getState().equals(State.STRIKE.getResult())) {
                return Pin.toPass(ball, remainingPins);
            }
            return Pin.toSpare(ball, remainingPins);
        }

        if (ball.getPitchNumber() == 1 && remainingPins == Pin.initPins) { // 1회차 거터
            return Pin.toGutter(ball, remainingPins);
        }

        if (ball.getPitchNumber() == 2 && remainingPins == pins.get(0).getRemainingPins()) { // 2회차 거터
            return Pin.toGutter(ball, remainingPins);
        }

        return Pin.toMiss(ball, remainingPins); // 핀이 낱개로 남음
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

    public Pin getPinsIndex(int index) {
        return pins.get(index);
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
