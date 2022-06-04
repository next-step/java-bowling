package bowling.domain.pin;

import bowling.domain.Hit;
import bowling.exception.InvalidHitSizeException;
import bowling.exception.OverflowPinException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NormalFramePins implements Pins {

    private final List<Hit> hits;

    public NormalFramePins() {
        this(new ArrayList<>());
    }

    public NormalFramePins(int... hits) {
        this(toList(hits));
    }

    public NormalFramePins(List<Hit> hits) {
        if (hits.size() > MAX_HITS_SIZE) {
            throw new IllegalArgumentException();
        }
        this.hits = hits;
    }

    private static List<Hit> toList(int... hits) {
        return IntStream.of(hits)
                .boxed()
                .map(Hit::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public void fallDown(int hit, boolean isBonusHit) {
        validate(hit, isBonusHit);
        hits.add(Hit.valueOf(hit));
    }

    private void validate(int hit, boolean isBonusHit) {
        if (isBonusHit) {
            throw new IllegalStateException();
        }
        if (hits.size() >= MAX_HITS_SIZE) {
            throw new InvalidHitSizeException(MAX_HITS_SIZE);
        }
        if (isFirstRound()) {
            return ;
        }
        validateMaxHit(firstHit(), hit);
    }

    private void validateMaxHit(int hit, int anotherHit) {
        if (hit + anotherHit <= Hit.MAX_NUMBER) {
            return;
        }
        throw new OverflowPinException(hit, anotherHit);
    }

    private boolean isFirstRound() {
        return hits.isEmpty();
    }

    @Override
    public int firstHit() {
        return hits.get(FIRST_HIT_INDEX).toInt();
    }

    @Override
    public int secondHit() {
        return hits.get(SECOND_HIT_INDEX).toInt();
    }

    @Override
    public int thirdHit() {
        return hits.get(THIRD_HIT_INDEX).toInt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFramePins normalFramePins = (NormalFramePins) o;
        return Objects.equals(hits, normalFramePins.hits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hits);
    }
}
