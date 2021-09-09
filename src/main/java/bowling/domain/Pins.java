package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Pins {

    public static final int MAX_DOWNED_PIN_SIZE = 10;
    public static final int GUTTER = 0;

    private final List<Boolean> pins;
    private Status status;

    private Pins(List<Boolean> pins, Status status) {
        this.pins = pins;
        this.status = status;
    }

    public static Pins create() {
        return new Pins(new ArrayList<>(), Status.MISS);
    }

    public static Pins of(Pins pins, Status status) {
        return new Pins(new ArrayList<>(pins.pins), status);
    }

    public static Pins from(Pins pins) {
        return new Pins(new ArrayList<>(pins.pins), pins.status);
    }

    public int numberOfPinDowns() {
        return pins.size();
    }

    public boolean isAllDown() {
        return pins.size() == MAX_DOWNED_PIN_SIZE;
    }

    public Pins roll(int down) {
        int standPinsCount = numberOfPinDowns();

        if (standPinsCount + down > MAX_DOWNED_PIN_SIZE) {
            throw new IllegalArgumentException("11개 이상의 볼을 쓰러뜨릴 수 없습니다.");
        }

        pins.clear();

        for (int i = 0; i < down; i++) {
            pins.add(true);
        }

        if (down == MAX_DOWNED_PIN_SIZE) {
            status = Status.STRIKE;
            return Pins.of(this, Status.STRIKE);
        }

        if (standPinsCount + down == MAX_DOWNED_PIN_SIZE) {
            status = Status.SPARE;
            return Pins.of(this, Status.SPARE);
        }

        if (down == GUTTER) {
            status = Status.GUTTER;
            return Pins.of(this, Status.GUTTER);
        }

        status = Status.MISS;
        return Pins.of(this, Status.MISS);
    }

    public boolean isSpare() {
        return status.equals(Status.SPARE);
    }

    public boolean isStrike() {
        return status.equals(Status.STRIKE);
    }

    public Status status() {
        return status;
    }
}
