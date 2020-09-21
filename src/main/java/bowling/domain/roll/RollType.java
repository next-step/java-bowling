package bowling.domain.roll;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

import static bowling.domain.Pins.DEFAULT_PIN_COUNT;

public enum RollType {

    STRIKE("X", (knocked, remained) -> knocked == DEFAULT_PIN_COUNT && remained == 0),

    GUTTER("-", (knocked, remained) -> knocked == 0),

    SPARE("/", (knocked, remained) -> knocked > 0 && remained == 0),

    DEFAULT("%d", (knocked, remained) -> knocked > 0);

    private final String markingPattern;
    private final BiPredicate<Integer, Integer> typeMatch;

    RollType(String markingPattern, BiPredicate<Integer, Integer> typeMatch) {
        this.markingPattern = markingPattern;
        this.typeMatch = typeMatch;
    }

    public String marking(int countOfKnockedPins) {
        return String.format(markingPattern, countOfKnockedPins);
    }

    public static RollType valueOf(int countOfKnocked, int countOfRemain) {
        return Stream.of(values())
                .filter(rollType -> rollType.test(countOfKnocked, countOfRemain))
                .findFirst()
                .orElse(DEFAULT);
    }

    private boolean test(int countOfKnocked, int countOfRemain) {
        return typeMatch.test(countOfKnocked, countOfRemain);
    }
}
