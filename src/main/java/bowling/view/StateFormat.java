package bowling.view;

import bowling.domain.dto.StateData;
import bowling.domain.state.pitching.FirstPitching;
import bowling.domain.state.pitching.SecondPitching;
import bowling.domain.state.result.Miss;
import bowling.domain.state.result.Spare;
import bowling.domain.state.result.Strike;

import java.util.Arrays;
import java.util.function.Function;

public enum StateFormat {
    STRIKE(Strike.class.getSimpleName(), state -> String.format("%s", RecordMark.STRIKE.getMark())),
    SPARE(Spare.class.getSimpleName(), state -> String.format("%s|/", RecordMark.find(state.getCountFirstPins()))),
    MISS(Miss.class.getSimpleName(), state -> String.format("%s|%s",
            RecordMark.find(state.getCountFirstPins()),
            RecordMark.find(state.getCountSecondPins()))),
    FIRST_PITCHING(FirstPitching.class.getSimpleName(), state -> ""),
    SECOND_PITCHING(SecondPitching.class.getSimpleName(), state -> RecordMark.find(state.getCountFirstPins()));

    private final String type;
    private final Function<StateData, String> changeFunc;

    StateFormat(String type, Function<StateData, String> changeFunc) {
        this.type = type;
        this.changeFunc = changeFunc;
    }

    public static String getValue(StateData stateData) {
        return Arrays.stream(values())
                .filter(key -> key.type.equals(stateData.getType()))
                .findFirst()
                .map(format -> format.changeFunc.apply(stateData))
                .orElseThrow(IllegalArgumentException::new);
    }

}
