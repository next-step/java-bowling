package bowling.domain.core;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;

import static java.util.stream.Collectors.toList;

public final class Pins {
    static final int MIN_FALLEN_PIN_COUNT = 0;
    static final int MAX_FALLEN_PIN_COUNT = 10;
    static final String ERROR_MESSAGE = "지정된 넘어진 핀 갯수가 잘못되었습니다.";
    static final String ERROR_MESSAGE_SECOND_BOWL = "두번째 투구에서 핀남은 핀보다 많이 쓰러뜨릴 수 없습니다.";
    private static final List<Pins> cachedPins;
    private final int fallenPins;

    static {
        final List<Pins> pins = IntStream.rangeClosed(MIN_FALLEN_PIN_COUNT, MAX_FALLEN_PIN_COUNT)
                                         .mapToObj(Pins::new)
                                         .collect(toList());
        cachedPins = Collections.unmodifiableList(pins);
    }

    private Pins(int fallenPins) {
        this.fallenPins = fallenPins;
    }

    public static Pins of(int fallenPinCount) {
        verifyPins(fallenPinCount);
        return cachedPins.get(fallenPinCount);
    }

    public static Pins zero(){
        return Pins.of(MIN_FALLEN_PIN_COUNT);
    }

    private static void verifyPins(int fallenPinCount) {
        if (MIN_FALLEN_PIN_COUNT > fallenPinCount || MAX_FALLEN_PIN_COUNT < fallenPinCount) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    private boolean canNotSpendRemainingPins(Pins secondRoll) {
        int remainingPins = Pins.MAX_FALLEN_PIN_COUNT - getFallenPins();
        return (0 != remainingPins) && (remainingPins < secondRoll.getFallenPins());
    }

    void verifySecondBowlFallenPins(Pins secondRoll) {
        if (canNotSpendRemainingPins(secondRoll)){
            throw new IllegalArgumentException(ERROR_MESSAGE_SECOND_BOWL);
        }
    }

    int getFallenPins() {
        return fallenPins;
    }

    int plus(Pins secondFallenPins){
        return getFallenPins() + secondFallenPins.getFallenPins();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pins pins = (Pins) o;
        return getFallenPins() == pins.getFallenPins();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getFallenPins());
    }

    @Override
    public String toString() {
        return "Pins(" + fallenPins + ')';
    }
}
