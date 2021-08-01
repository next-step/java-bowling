package bowling.view.state;

import bowling.domain.state.*;
import bowling.dto.StateDto;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum StateFormat {
    PREPARATION(Preparation.class, state -> ""),
    INPROGRESS(InProgress.class, state -> String.format("%s", NumberSymbol.convert(state.getFirstDownedPins()))),
    SPARE(Spare.class, state -> String.format("%s|/", NumberSymbol.convert(state.getFirstDownedPins()))),
    STRIKE(Strike.class, state -> String.format("%s", NumberSymbol.convert(state.getFirstDownedPins()))),
    MISS(Miss.class, state -> String.format(
            "%s|%s",
            NumberSymbol.convert(state.getFirstDownedPins()),
            NumberSymbol.convert(state.getSecondDownedPins()))
    );

    private static final Map<Class<? extends State>, StateFormat> STATE_FORMATS =
            Arrays.stream(values())
                    .collect(Collectors.toMap(StateFormat::getStateType, Function.identity()));

    private final Class<? extends State> stateType;
    private final Function<StateDto, String> converter;

    StateFormat(final Class<? extends State> stateType, final Function<StateDto, String> converter) {
        this.stateType = stateType;
        this.converter = converter;
    }

    public static String convert(StateDto stateDto) {
        if (!STATE_FORMATS.containsKey(stateDto.getStateClass())) {
            throw new IllegalArgumentException("There is no format for " + stateDto.getStateClass());
        }

        return STATE_FORMATS.get(stateDto.getStateClass())
                .converter.apply(stateDto);
    }

    private Class<? extends State> getStateType() {
        return stateType;
    }

}
