package bowling.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Pitches implements Iterable<Pitch> {

    private static final int STRIKE_COUNT = 10;

    private final List<Pitch> values;

    public Pitches() {
        this(new ArrayList<>());
    }

    public Pitches(List<Pitch> values) {
        this.values = values;
    }

    public int pinDownCount() {
        return values.stream()
                .mapToInt(Pitch::value)
                .sum();
    }

    public int count() {
        return values.size();
    }

    public void add(Pitch pitch) {
        values.add(pitch);
    }

    public boolean isStrike() {
        return count() == 1 && pinDownCount() == STRIKE_COUNT;
    }

    public int spare() {
        return STRIKE_COUNT - pinDownCount();
    }

    @Override
    public Iterator<Pitch> iterator() {
        return values.iterator();
    }

    @Override
    public void forEach(Consumer<? super Pitch> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Pitch> spliterator() {
        return Iterable.super.spliterator();
    }
}
