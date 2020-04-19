package bowling.view;

import bowling.Pin;
import bowling.framestate.State;
import bowling.framestate.common.*;
import bowling.framestate.last.*;

import java.util.*;
import java.util.function.Function;

public enum FrameScoreConsoleResult {
    READY(pins -> Collections.singletonList("")),
    FIRST_BOWL(pins -> Collections.singletonList(String.valueOf(pins.get(0)))),
    MISS(pins -> Arrays.asList(String.valueOf(pins.get(0)), String.valueOf(pins.get(1)))),
    SPARE(pins -> Arrays.asList(String.valueOf(pins.get(0)), "/")),
    STRIKE(pins -> Collections.singletonList("X")),
    READY_LAST_FRAME(pins -> Collections.singletonList("")),
    FIRST_BOWL_LAST_FRAME(pins -> Collections.singletonList(String.valueOf(pins.get(0)))),
    SPARE_LAST_FRAME(pins -> Arrays.asList(String.valueOf(pins.get(0)), "/")),
    SPARE_LAST_FRAME_OVER(pins -> Arrays.asList(String.valueOf(pins.get(0)), "/", String.valueOf(pins.get(2)))),
    STRIKE_LAST_FRAME(pins -> Collections.singletonList("X")),
    STRIKE_LAST_FRAME_OVER(pins -> Arrays.asList("X", String.valueOf(pins.get(1)))),
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
        FRAME_STATES.put(ReadyLastFrame.class, READY_LAST_FRAME);
        FRAME_STATES.put(FirstBowlLastFrame.class, FIRST_BOWL_LAST_FRAME);
        FRAME_STATES.put(SpareLastFrame.class, SPARE_LAST_FRAME);
        FRAME_STATES.put(SpareLastFrameOver.class, SPARE_LAST_FRAME_OVER);
        FRAME_STATES.put(StrikeLastFrame.class, STRIKE_LAST_FRAME);
        FRAME_STATES.put(StrikeLastFrameOver.class, STRIKE_LAST_FRAME_OVER);
    }

    private final Function<List<Pin>, List<String>> printFunction;

    FrameScoreConsoleResult(final Function<List<Pin>, List<String>> printFunction) {
        this.printFunction = printFunction;
    }

    public static FrameScoreConsoleResult of(final State state) {
        return FRAME_STATES.get(state.getClass());
    }

    public String toString(final List<Pin> pins) {
        return joinScoreString(printFunction.apply(pins));
    }

    private static String joinScoreString(List<String> scores) {
        return String.join(SCORE_DELIMITER, scores);
    }
}
