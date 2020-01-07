package bowling.retry;

import java.util.LinkedList;
import java.util.Objects;

public class Frame {

    private static final int INIT_VALUE = 0;

    private int index;
    private int chanceOfCount;
    private LinkedList<Pin> pins;

    public Frame(int index) {
        this.index = index;
        this.chanceOfCount = 2;
        createPins();
    }

    public Frame() {
        this.index = 9;
        this.chanceOfCount = 3;
        createPins();
    }

    private void createPins() {
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

    public String getStatus() {
        if (index < 9) {
            return new FrameStatus(pins).getStatus();
        }
        return new FinalFrameStatus(pins).getStatus();

    }

    public int getChanceCount() {
        return chanceOfCount - pins.size();
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
