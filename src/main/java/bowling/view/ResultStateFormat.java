package bowling.view;

import bowling.domain.dto.StateDatum;
import bowling.domain.state.pitching.FirstPitching;
import bowling.domain.state.pitching.SecondPitching;
import bowling.domain.state.result.Miss;
import bowling.domain.state.result.Spare;
import bowling.domain.state.result.Strike;

import java.util.Arrays;
import java.util.function.Function;

public enum ResultStateFormat {
    STRIKE(Strike.class.getSimpleName(), state -> String.format("%s", BowlingRecordMark.STRIKE.getMark())),
    SPARE(Spare.class.getSimpleName(), state -> String.format("%s|/", BowlingRecordMark.find(state.getCountFirstPins()))),
    MISS(Miss.class.getSimpleName(), state -> String.format("%s|%s",
            BowlingRecordMark.find(state.getCountFirstPins()),
            BowlingRecordMark.find(state.getCountSecondPins()))),
    FIRST_PITCHING(FirstPitching.class.getSimpleName(), state -> ""),
    SECOND_PITCHING(SecondPitching.class.getSimpleName(), state -> BowlingRecordMark.find(state.getCountFirstPins()));

    private final String type;
    private final Function<StateDatum, String> changeFunc;

    ResultStateFormat(String type, Function<StateDatum, String> changeFunc) {
        this.type = type;
        this.changeFunc = changeFunc;
    }

    public static String getValue(StateDatum stateDatum) {
        return Arrays.stream(values())
                .filter(key -> key.type.equals(stateDatum.getType()))
                .findFirst()
                .map(format -> format.changeFunc.apply(stateDatum))
                .orElseThrow(IllegalArgumentException::new);
    }

}
