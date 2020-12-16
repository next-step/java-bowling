package bowling.domain.pitchings;

import bowling.domain.KnockDownPins;
import bowling.domain.Pitching;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class Pitchings implements Iterable<Pitching> {
    protected final List<Pitching> value;

    public Pitchings() {
        value = new ArrayList<>();
    }

    public Optional<Pitching> getFirstPitching() {
        if (value.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(value.get(0));
    }

    public Optional<Pitching> getSecondPitching() {
        if (value.size() < 2) {
            return Optional.empty();
        }
        return Optional.of(value.get(1));
    }

    public Stream<Pitching> stream() {
        return value.stream();
    }

    public boolean contains(Pitching pitching) {
        return value.contains(pitching);
    }

    public int calculateTotalScore() {
        if (isSpare()) {
            return Pitching.SPARE.getScore();
        }

        return value.stream()
                .mapToInt(Pitching::getScore)
                .sum();
    }

    private boolean isSpare() {
        return value.contains(Pitching.SPARE);
    }

    protected void setFirstPitching(KnockDownPins knockDownPins) {
        Pitching pitching = Pitching.getPitching(knockDownPins);
        value.add(pitching);
    }

    protected void setSecondPitching(KnockDownPins knockDownPins) {
        int lastIndex = value.size() - 1;
        Pitching previousPitching = value.get(lastIndex);
        Pitching pitching = Pitching.getPitching(knockDownPins, previousPitching);
        value.add(pitching);
    }

    @Override
    public Iterator<Pitching> iterator() {
        return value.iterator();
    }

    public abstract void addPitching(KnockDownPins knockDownPins);

    public abstract boolean isEnd();

    public abstract Optional<Integer> getTotalScoreWithStrikeBonus(Optional<Pitching> nextPitching, Optional<Pitching> nextAndNextPitching);

    public abstract Optional<Integer> calculateTotalScoreWithSpareBonus(Optional<Pitching> nextPitching);
}
