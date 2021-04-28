package bowlingRefactor.domain.state.middelState;

import bowlingRefactor.domain.Pin;
import bowlingRefactor.domain.Score;
import bowlingRefactor.domain.state.State;

import java.util.*;

public abstract class Progress implements State {

    List<Pin> pins;

    @Override
    public int sum() {
        if (emptyPin()) {
            return 0;
        }
        return pins.stream()
                .mapToInt(Pin::getPin)
                .sum();
    }

    @Override
    public List<Pin> getPins() {
        if (emptyPin()) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(pins);
    }

    @Override
    public Score addBonus(Score score) {
        if (emptyPin()) {
            return score;
        }

        for (Pin pin : pins) {
            score = score.addBonus(pin);
        }
        return score;
    }

    @Override
    public Score getScore(int totalStore) {
        return Score.ofNoneScore();
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    public List<Pin> nextPins(int countOfPin) {
        if (emptyPin()) {
            return Arrays.asList(Pin.of(countOfPin));
        }

        List<Pin> nextPins = new ArrayList<>(this.pins);
        nextPins.add(Pin.of(countOfPin));

        return nextPins;
    }

    private boolean emptyPin() {
        if (Objects.isNull(pins)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Progress progress = (Progress) o;
        return Objects.equals(pins, progress.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
