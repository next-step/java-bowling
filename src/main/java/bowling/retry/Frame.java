package bowling.retry;

import java.util.LinkedList;
import java.util.Objects;

public class Frame {

    private static final int CHANCE_COUNT = 2;
    private static final int INIT_VALUE = 0;

    private int index;
    private LinkedList<Pin> pins;

    public Frame(int index) {
        this.index = index;
        this.pins = new LinkedList<>();
    }

    public void bowl(int countOfHit) {
        validate();
        pins.offer(new Pin(countOfHit));
    }

    private void validate() {
        if (getChanceCount() == 0) {
            throw new IllegalArgumentException("남은 기회가 없습니다.");
        }
    }

    public boolean isNext() {
        return isStrike() || getChanceCount() == 0;
    }

    public boolean isStrike() {
        return getScore() == 10 && getChanceCount() == 1;
    }

    public int getScore() {
        return pins.stream()
                .map(Pin::getCountOfHit)
                .reduce(Integer::sum)
                .orElse(INIT_VALUE)
                ;
    }

    public int getChanceCount() {
        return CHANCE_COUNT - pins.size();
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
