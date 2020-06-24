package bowling.domain.dto;

import bowling.domain.pin.PinExpression;
import bowling.domain.state.State;
import bowling.domain.state.StateExpression;
import bowling.domain.state.finish.Miss;
import bowling.domain.state.finish.Spare;
import bowling.domain.state.finish.Strike;
import bowling.domain.state.running.FirstHit;
import bowling.domain.state.running.Ready;
import bowling.exception.message.ErrorMessage;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum StateConverter {

    READY(Ready.class,
            stateDto -> StateExpression.READY),
    FIRST_HIT(FirstHit.class,
            stateDto -> PinExpression.convert(stateDto.getFirstPinCount())),
    STRIKE(Strike.class,
            stateDto -> StateExpression.STRIKE),
    SPARE(Spare.class,
            stateDto -> PinExpression.convert(stateDto.getFirstPinCount())
                    + StateExpression.DELIMITER
                    + StateExpression.SPARE),
    MISS(Miss.class,
            stateDto -> PinExpression.convert(stateDto.getFirstPinCount())
                    + StateExpression.DELIMITER
                    + PinExpression.convert(stateDto.getSecondPinCount()));

    private final Class<? extends State> stateClassType;
    private final Function<StateDto, String> converter;

    private static final Map<Class<? extends State>, StateConverter> CONVERTERS =
            Arrays.stream(values())
                    .collect(Collectors.toMap(StateConverter::getStateClassType, Function.identity()));

    StateConverter(final Class<? extends State> stateClassType, final Function<StateDto, String> converter) {
        this.stateClassType = stateClassType;
        this.converter = converter;
    }

    public static String convertToSymbol(final StateDto stateDto) {
        validate(stateDto);
        return CONVERTERS.get(stateDto.getStateClassType())
                .converter
                .apply(stateDto);
    }

    private static void validate(final StateDto stateDto) {
        if (Objects.isNull(stateDto)) {
            throw new IllegalArgumentException(ErrorMessage.NULL_VALUE);
        }
        if (!CONVERTERS.containsKey(stateDto.getStateClassType())) {
            throw new IllegalArgumentException(ErrorMessage.NOT_EXIST_STATE);
        }
    }

    private Class<? extends State> getStateClassType() {
        return this.stateClassType;
    }
}
