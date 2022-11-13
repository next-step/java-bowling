package bowling.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Pins {
    private LinkedList<Pin> values;
    private static final int MAX_TIMES = 2;
    private static final int MAX_SCORE = 10;

    private Pins(LinkedList<Pin> values) {
        this.values = values;
    }

    public static Pins of(Pin pin) {
        LinkedList<Pin> pins = new LinkedList<>();
        pins.add(pin);
        return new Pins(pins);
    }

    public void bowl(Pin pin) {
        if (isFinished()) {
            throw new IllegalStateException("턴이 종료되었습니다.");
        }
        if (getSum() + pin.getValue() > MAX_SCORE) {
            throw new IllegalArgumentException();
        }
        values.add(pin);
    }

    public boolean isSpare() {
        return isFinished() && getSum() == MAX_SCORE;
    }

    public boolean isMiss() {
        return isFinished() && getSum() < MAX_SCORE;
    }

    private int getSum() {
        return values.stream().mapToInt(Pin::getValue).sum();
    }

    public List<Integer> getRecord(){
        return values.stream().map(Pin::getValue).collect(Collectors.toUnmodifiableList());
    }

    public int getRemainPins(){
        return MAX_SCORE - getSum();
    }

    private boolean isFinished(){
        return values.size() == MAX_TIMES || values.stream().anyMatch(Pin::isStrike);
    }
}
