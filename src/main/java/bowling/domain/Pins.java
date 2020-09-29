package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Pins {

    private final List<Pin> pins;

    public Pins() {
        this.pins = new ArrayList<>();
    }

    public void pitch(int count) {
        if (pins.isEmpty() || getLastPin().isEnd()) {
            pins.add(new Pin(count));
            return;
        }

        pins.add(getLastPin().next(count));
    }

    private Pin getLastPin() {
        if (pins.isEmpty()) {
            throw new IllegalArgumentException("가져올 수 있는 핀이 없습니다.");
        }

        return pins.get(pins.size() - 1);
    }

    public boolean isEnd() {
        return getLastPin().isEnd();
    }

    public boolean overPitching(int maxPitchCount) {
        return pins.size() >= maxPitchCount;
    }

    public List<Pin> getPins() {
        return pins;
    }

    public List<String> getScore() {
        return pins.stream()
                .map(Pin::getSymbolValue)
                .collect(Collectors.toList());
    }

    public boolean isEmpty() {
        return pins.isEmpty();
    }

    public boolean hasEndScore() {
        return pins.stream().anyMatch(Pin::isEnd);
    }

    public Score getTotalScore() {
        if (getLastPin().getSymbol().equals(ScoreSymbol.STRIKE)) {
            return new Score(10, 2);
        }

        if (getLastPin().getSymbol().equals(ScoreSymbol.SPARE)) {
            return new Score(10, 2);
        }

        return new Score(getSum(), 0);
    }

    public int getSum() {
        return pins.stream().mapToInt(Pin::getCount).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins1 = (Pins) o;
        return Objects.equals(pins, pins1.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
