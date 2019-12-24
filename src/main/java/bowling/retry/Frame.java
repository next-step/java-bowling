package bowling.retry;

import java.util.LinkedList;
import java.util.Objects;

public class Frame {

    private static final int INIT_VALUE = 0;

    private int index;
    private LinkedList<Pin> pins;

    public Frame(int index) {
        this.index = index;
        this.pins = new LinkedList<>();
    }

    public void bowl(int countOfHit) {
        pins.offer(new Pin(countOfHit));
    }

    public int getScore() {
        return pins.stream()
                .map(Pin::getCountOfHit)
                .reduce(Integer::sum)
                .orElse(INIT_VALUE)
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return index == frame.index &&
                Objects.equals(pins, frame.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, pins);
    }

}
