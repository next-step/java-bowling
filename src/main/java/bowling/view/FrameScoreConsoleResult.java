package bowling.view;

import bowling.Pin;
import bowling.dto.FrameState;
import bowling.framestate.*;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public enum FrameScoreConsoleResult {
    READY((pins, bonusPin) -> Collections.singletonList("")),
    FIRST_BOWL((pins, bonusPin) -> Collections.singletonList(String.valueOf(pins.get(0)))),
    MISS((pins, bonusPin) -> Arrays.asList(String.valueOf(pins.get(0)), String.valueOf(pins.get(1)))),
    SPARE((pins, bonusPin) -> Arrays.asList(String.valueOf(pins.get(0)), "/", printBonusPin(bonusPin))),
    STRIKE((pins, bonusPin) -> Arrays.asList("X", printBonusPin(bonusPin))),
    ;

    private static final String SCORE_DELIMITER = "|";
    private static final Map<Class, FrameScoreConsoleResult> FRAME_STATES;

    static {
        FRAME_STATES = new HashMap<>();
        FRAME_STATES.put(Ready.class, READY);
        FRAME_STATES.put(FirstBowl.class, FIRST_BOWL);
        FRAME_STATES.put(Miss.class, MISS);
        FRAME_STATES.put(Spare.class, SPARE);
        FRAME_STATES.put(Strike.class, STRIKE);
    }

    private final BiFunction<List<Pin>, Pin, List<String>> printFunction;

    FrameScoreConsoleResult(final BiFunction<List<Pin>, Pin, List<String>> printFunction) {
        this.printFunction = printFunction;
    }

    public static FrameScoreConsoleResult of(final State state) {
        return FRAME_STATES.get(state.getClass());
    }

    public String toString(final FrameState frameState) {
        List<String> test = printFunction.apply(frameState.getPins(), frameState.getBonusPin());
        return joinScoreString(test);
    }

    private static String printBonusPin(final Pin bonusPin) {
        if (Objects.isNull(bonusPin)) {
            return "";
        }

        if (bonusPin.isEqualTo(10)) {
            return "X";
        }

        return bonusPin.toString();
    }

    private static String joinScoreString(final List<String> scores) {
        List<String> filteredScoreStrings = scores.stream()
                .filter(score -> !score.isEmpty())
                .collect(Collectors.toList());

        return String.join(SCORE_DELIMITER, filteredScoreStrings);
    }
}
