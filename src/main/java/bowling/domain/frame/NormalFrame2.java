package bowling.domain.frame;

import java.util.List;

public class NormalFrame2 extends Frame2 {

    public NormalFrame2() {
    }

    public NormalFrame2(int pins) {
        super(pins);
    }

    public NormalFrame2(int pins, List<Integer> fallenPins) {
        super(pins, fallenPins);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
