package bowling.view;

import bowling.dto.StateDto;
import bowling.state.*;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum StateView {
    READY(Ready.class, state -> ""),
    PROGRESS(Progress.class, state -> String.format("%s", firstDownedPinsSymbol(state))),
    SPARE(Spare.class, state -> String.format("%s|/", firstDownedPinsSymbol(state))),
    STRIKE(Strike.class, state -> String.format("%s", firstDownedPinsSymbol(state))),
    MISS(Miss.class, state -> String.format("%s|%s", firstDownedPinsSymbol(state), secondDownedPinsSymbol(state)));

    private static final Map<Class<? extends State>, StateView> STATE_MAP;

    private final Class<? extends State> stateType;
    private final Function<StateDto, String> stateConverter;

    static {
        STATE_MAP = Stream.of(values())
                .collect(toMap(StateView::getStateType, Function.identity()));
    }

    StateView(final Class<? extends State> stateType, final Function<StateDto, String> stateConverter) {
        this.stateType = stateType;
        this.stateConverter = stateConverter;
    }

    public static String convert(final StateDto stateDto) {
        verifyContainsKey(stateDto);
        return STATE_MAP.get(stateDto.getStateType())
                .stateConverter.apply(stateDto);
    }

    private static void verifyContainsKey(final StateDto stateDto) {
        final Class<? extends State> stateType = stateDto.getStateType();
        if (!STATE_MAP.containsKey(stateType)) {
            throw new IllegalArgumentException(String.format("맵에서 %s를 찾을 수 없습니다", stateType.getSimpleName()));
        }
    }

    private static String firstDownedPinsSymbol(final StateDto state) {
        return ScoreSymbol.convert(state.getFirstDownedPins());
    }

    private static String secondDownedPinsSymbol(final StateDto state) {
        return ScoreSymbol.convert(state.getSecondDownedPins());
    }

    private Class<? extends State> getStateType() {
        return stateType;
    }

    public enum ScoreSymbol {
        GUTTER(0, "-"),
        STRIKE(10, "X");

        private static final Map<Integer, String> SCORE_SYMBOLS_MAP;

        private final int score;
        private final String symbol;

        static {
            SCORE_SYMBOLS_MAP = Stream.of(values())
                    .collect(toMap(ScoreSymbol::getScore, ScoreSymbol::getSymbol));
        }

        ScoreSymbol(final int score, final String symbol) {
            this.score = score;
            this.symbol = symbol;
        }

        private int getScore() {
            return score;
        }

        private String getSymbol() {
            return symbol;
        }

        private static String convert(final int score) {
            return SCORE_SYMBOLS_MAP.getOrDefault(score, String.valueOf(score));
        }
    }
}
