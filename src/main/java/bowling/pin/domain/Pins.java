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

    public static Pins playPitch(String point) {
        int pitchNumber = 1;
        while (pitchNumber < DEFAULT_PITCH_COUNT) {
            Pin pin = Pin.firstPitch(Ball.pitch(point, pitchNumber));
            pins.add(pin);
            pitchNumber += 1;
        }
        return new Pins(pins);
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

}
