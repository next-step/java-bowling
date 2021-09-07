package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Pins {

    public static final int DEFAULT_PINS_SIZE = 10;
    private final List<Boolean> pins;

    private Pins(List<Boolean> pins) {
        this.pins = pins;
    }


    public static Pins create() {
        return new Pins(new ArrayList<>());
    }

    public static Pins from(Pins pins) {
        return new Pins(new ArrayList<>(pins.pins));
    }


    public int numberOfPinDowns() {
        return pins.size();
    }

    public boolean isAllDown() {
        return pins.size() == DEFAULT_PINS_SIZE;
    }

    public int roll(int down) {
        int standPinsCount = numberOfPinDowns();

        if (standPinsCount + down > DEFAULT_PINS_SIZE) {
                throw new IllegalArgumentException("11개 이상의 볼을 쓰러뜨릴 수 없습니다.");
        }

        for (int i = 0; i < down; i++) {
            pins.add(true);
        }

        return standPinsCount + down;
    }
}
