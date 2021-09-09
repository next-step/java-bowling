package bowling.domain.pins;

import bowling.domain.Status;
import java.util.ArrayList;
import java.util.List;

public class Pins {

    public static final int MAX_DOWNED_PIN_SIZE = 10;

    private final List<Boolean> pins;
    private Status status;

    private Pins(List<Boolean> pins, Status status) {
        this.pins = pins;
        this.status = status;
    }

    public static Pins create() {
        return new Pins(new ArrayList<>(), Status.MISS);
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

        changeStatus(down, standPinsCount);
        return Pins.from(this);
    }

    private void changeStatus(int down, int standPinsCount) {
        if (standPinsCount + down == MAX_DOWNED_PIN_SIZE) {
            status = Status.find(MAX_DOWNED_PIN_SIZE, true);
            return;
        }

        status = Status.find(down, false);
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
