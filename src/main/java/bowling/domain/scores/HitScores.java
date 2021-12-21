package bowling.domain.scores;

import bowling.Pin;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class HitScores {

    private static final int START_NUMBER = 0;
    private static final int FIRST_FITCH = 1;
    private static final int GENERAL_FRAME_NUMBER = 2;
    private static final int PIN_MAX_NUMBER = 10;

    protected final List<Pin> hitPins;

    public HitScores(List<Pin> hitPins) {
        this.hitPins = hitPins;
    }

    public abstract HitScores add(int hitCount);

    public abstract boolean isClosed();

    public final boolean containStrike() {
        return hitPins.stream()
            .anyMatch(Pin::isStrike);
    }

    public final boolean containSpare() {
        if (hitPins.size() < GENERAL_FRAME_NUMBER) {
            return false;
        }

        return sumFirstAndSecond() == PIN_MAX_NUMBER;
    }

    public final boolean isMiss() {
        if (hitPins.size() < GENERAL_FRAME_NUMBER) {
            return false;
        }

        return hitPins.get(FIRST_FITCH).equals(Pin.of(START_NUMBER));
    }

    public final boolean isGutter() {
        return hitPins.stream()
            .allMatch(Pin::isNonHit);
    }


    public final int sumScore() {
        return hitPins.stream()
            .mapToInt(Pin::getHitCount)
            .sum();
    }

    protected final int sumFirstAndSecond() {
        return IntStream.range(START_NUMBER, GENERAL_FRAME_NUMBER)
            .boxed()
            .map(hitPins::get)
            .mapToInt(Pin::getHitCount)
            .sum();
    }

    protected static List<Pin> toPins(int[] numbers) {
        return Arrays.stream(numbers)
            .boxed()
            .map(Pin::of)
            .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HitScores hitScores = (HitScores) o;
        return Objects.equals(hitPins, hitScores.hitPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hitPins);
    }
}
