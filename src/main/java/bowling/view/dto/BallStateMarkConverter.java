package bowling.view.dto;

import bowling.model.Pins;
import bowling.model.frame.BallState;
import bowling.model.frame.state.FirstThrown;
import bowling.model.frame.state.NotThrown;
import bowling.model.frame.state.SecondThrown;
import bowling.model.frame.state.Spare;
import bowling.model.frame.state.Strike;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

enum BallStateMarkConverter {

    STRIKE(Strike.class, "x"),
    FIRST_THROWN(FirstThrown.class, "%s"),
    NOT_THROWN(NotThrown.class, ""),
    SECOND_THROWN(SecondThrown.class, "%s|%s"),
    SPARE(Spare.class, "%s|/");

    private static final String PINS_DELIMITER = "|";
    private static final String MAX_PINS_MESSAGE = "x";
    private static final String ZERO_PINS_MESSAGE = "-";

    private final Class<? extends BallState> stateClass;
    private final String format;

    BallStateMarkConverter(Class<? extends BallState> stateClass, String format) {
        this.stateClass = stateClass;
        this.format = format;
    }

    static String toString(BallState state, Collection<Integer> counts) {
        return Arrays.stream(values())
                .filter(response -> response.stateClass.isInstance(state))
                .map(response -> String.format(response.format, convertCounts(counts).toArray()))
                .findAny()
                .orElseGet(() -> defaultMessage(counts));
    }

    public static String toString(BallState state, List<Integer> counts, List<Integer> additionHitPinsGroup) {
        if (additionHitPinsGroup.isEmpty()) {
            return toString(state, counts);
        }
        return String.join(PINS_DELIMITER, toString(state, counts), defaultMessage(additionHitPinsGroup));
    }

    static String defaultMessage(Collection<Integer> counts) {
        return String.join(PINS_DELIMITER, convertCounts(counts));
    }

    private static List<String> convertCounts(Collection<Integer> counts) {
        return counts.stream()
                .map(BallStateMarkConverter::convertMessage)
                .collect(Collectors.toList());
    }

    private static String convertMessage(Integer count) {
        if (count == Pins.MAX.count()) {
            return MAX_PINS_MESSAGE;
        }
        if (count == Pins.ZERO.count()) {
            return ZERO_PINS_MESSAGE;
        }
        return String.valueOf(count);
    }
}
