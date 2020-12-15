package bowling.domain.pitchings;

import bowling.domain.KnockDownPins;
import bowling.domain.Pitching;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public abstract class Pitchings implements Iterable<Pitching> {
    protected final List<Pitching> value;

    public Pitchings() {
        value = new ArrayList<>();
    }

    public abstract void addPitching(KnockDownPins knockDownPins);

    public abstract boolean isEnd();

    public Pitching getNextPitching() {
        if (value.isEmpty()) {
            return null;
        }
        return value.get(0);
    }

    public Pitching getNextAndNextPitching() {
        if (value.size() < 2) {
            return null;
        }
        return value.get(1);
    }

    public Stream<Pitching> stream() {
        return value.stream();
    }

    public boolean contains(Pitching pitching) {
        return value.contains(pitching);
    }

    public int getTotalScore() {
        return value.stream()
                .mapToInt(Pitching::getScore)
                .sum();
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
}
