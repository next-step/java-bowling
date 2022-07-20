package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Frame2 {
    protected int index;
    protected int restOfPins = 10;
    protected List<Integer> fallenPins = new ArrayList<>();
    protected Frame2 prev;
    protected Frame2 next;

    public abstract Frame2 askCurrentFrame();

    public void subtractPins(int fallenPin) {
        fallenPins.add(fallenPin);
        restOfPins -= fallenPin;
    };

    public Frame2() {
    }

    public Frame2(int index) {
        this.index = index;
    }

    public Frame2(int restOfPins, List<Integer> fallenPins) {
        this.restOfPins = restOfPins;
        this.fallenPins = fallenPins;
    }

    public Frame2(int index, int restOfPins, List<Integer> fallenPins) {
        this.index = index;
        this.restOfPins = restOfPins;
        this.fallenPins = fallenPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame2 frame2 = (Frame2) o;
        return restOfPins == frame2.restOfPins && Objects.equals(fallenPins, frame2.fallenPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restOfPins, fallenPins);
    }
}
