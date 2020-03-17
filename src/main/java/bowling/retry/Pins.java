package bowling.retry;

import java.util.ArrayList;
import java.util.List;

public class Pins {

    private List<Pin> pins;

    public Pins() {
        this.pins = new ArrayList<>();
    }

    public void add(int countOfHit) {
        validate(countOfHit);
        pins.add(new Pin(countOfHit));
    }

    private void validate(int countOfHit) {
        int totalHit = getCurrentHit() + countOfHit;
        if (totalHit > 10) {
            throw new IllegalArgumentException("잘 못된 점수 입니다.");
        }
    }

    public int getCurrentHit() {
        return pins.stream()
                .mapToInt(Pin::getCountOfHit)
                .sum();
    }

    public List<Pin> getPins() {
        return new ArrayList<>(pins);
    }
}
