package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Frame2 {
    private int pins = 10;
    private List<Integer> fallenPins = new ArrayList<>();

    public void subtractPins(int fallenPin) {
        fallenPins.add(fallenPin);
        pins -= fallenPin;
    };

    public Frame2() {
    }

    public Frame2(int pins) {
        this.pins = pins;
    }

    public Frame2(int pins, List<Integer> fallenPins) {
        this.pins = pins;
        this.fallenPins = fallenPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame2 frame2 = (Frame2) o;
        return pins == frame2.pins && Objects.equals(fallenPins, frame2.fallenPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins, fallenPins);
    }
}
