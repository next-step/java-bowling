package bowling.domain.pin;

import bowling.domain.Hit;
import bowling.exception.InvalidHitSizeException;
import bowling.exception.OverflowPinException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FinalFramePins implements Pins {

    private static final int MAX_HITS_SIZE_WITH_BONUS = 3;
    private final List<Hit> hits;

    public FinalFramePins() {
        this(new ArrayList<>());
    }

    public FinalFramePins(int... hits) {
        this(toList(hits));
    }

    public FinalFramePins(List<Hit> hits) {
        if (hits.size() > MAX_HITS_SIZE_WITH_BONUS) {
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
            validateBonus(hit);
            return;
        }
        if (hits.size() >= MAX_HITS_SIZE) {
            throw new InvalidHitSizeException(MAX_HITS_SIZE);
        }
        if (isFirstRound()) {
            return ;
        }
        validateMaxHit(firstHit(), hit);
    }

    private void validateBonus(int hit) {
        if (hits.size() > MAX_HITS_SIZE_WITH_BONUS) {
            throw new InvalidHitSizeException(MAX_HITS_SIZE_WITH_BONUS);
        }
        if (isFirstRound()) {
            throw new IllegalStateException();
        }
        if (isSecondRound()) {
            return;
        }
        if (secondHit() == Hit.MAX_NUMBER) {
            return;
        }
        if (firstHit() + secondHit() == Hit.MAX_NUMBER) {
            return;
        }
        validateMaxHit(secondHit(), hit);
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

    private boolean isSecondRound() {
        return hits.size() == ONE_HIT;
    }

    @Override
    public int firstHit() {
        if (hits.size() <= FIRST_HIT_INDEX) {
            return 0;
        }
        return hits.get(FIRST_HIT_INDEX).toInt();
    }

    @Override
    public int secondHit() {
        if (hits.size() <= SECOND_HIT_INDEX) {
            return 0;
        }
        return hits.get(SECOND_HIT_INDEX).toInt();
    }

    @Override
    public int thirdHit() {
        if (hits.size() <= THIRD_HIT_INDEX) {
            return 0;
        }
        return hits.get(THIRD_HIT_INDEX).toInt();
    }

    @Override
    public int totalHits() {
        return hits.stream()
                .mapToInt(Hit::toInt)
                .sum();
    }

    @Override
    public int hitSize() {
        return hits.size();
    }

    @Override
    public boolean hasSecondHit() {
        return hits.size() == MAX_HITS_SIZE;
    }

    @Override
    public boolean hasThirdHit() {
        return hits.size() == MAX_HITS_SIZE_WITH_BONUS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFramePins normalFramePins = (FinalFramePins) o;
        return Objects.equals(hits, normalFramePins.hits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hits);
    }
}
