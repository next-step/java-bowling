package step4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import step4.domain.BowlingSymbols;
import step4.domain.dto.PointDTO;
import step4.state.Symbol;
import step4.type.PitchesOrderType;
import step4.type.ResultPitchesType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BowlingPointsTest {

    @DisplayName("BowlingPoints 생성 테스트")
    @Test
    void createBowlingPoints() {
        BowlingSymbols bowlingSymbols = BowlingSymbols.of(2);
        assertThat(bowlingSymbols.size()).isEqualTo(0);
        assertThat(bowlingSymbols).isEqualTo(
                new BowlingSymbols(
                        new HashMap<>(),
                        2));
    }

    @DisplayName("push 테스트")
    @ParameterizedTest
    @CsvSource(value = {"10,0", "0,10", "5,5", "0,0", "1,5", "1,9"})
    void push(int first, int second) {
        HashMap<PitchesOrderType, Symbol> map = new HashMap<PitchesOrderType, Symbol>() {{
            put(PitchesOrderType.FIRST, ResultPitchesType.createSymbol(new PointDTO(2, 1, first, null, null)));
            put(PitchesOrderType.SECOND, ResultPitchesType.createSymbol(new PointDTO(2, 2, first, second, null)));
        }};
        BowlingSymbols points = BowlingSymbols.of(2);

        points.push(first);
        points.push(second);

        BowlingSymbols bowlingSymbols = new BowlingSymbols(map, 2);

        assertThat(points.getScore()).isEqualTo(bowlingSymbols.getScore());
    }

    @DisplayName("getScore 테스트")
    @ParameterizedTest
    @MethodSource("provideBowlingPointsAndScore")
    void getScore(BowlingSymbols symbols, int score) {
        assertThat(symbols.getScore()).isEqualTo(score);
    }

    private static Stream<Arguments> provideBowlingPointsAndScore() {
        BowlingSymbols symbols1 = BowlingSymbols.of(2).push(5)
                .push(5);

        BowlingSymbols symbols2 = BowlingSymbols.of(3).push(5)
                .push(5)
                .push(4);

        return Stream.of(
                Arguments.of(symbols1, 10),
                Arguments.of(symbols2, 14)
        );
    }

    @DisplayName("NormalFrame push 예외 테스트")
    @ParameterizedTest
    @MethodSource("providePitchesContAndValue")
    void normalPushWithException(int size, List<Integer> numbers, Class<RuntimeException> classType) {
        assertThatThrownBy(() -> {
            BowlingSymbols bowlingSymbols = BowlingSymbols.of(size);
            numbers.forEach(bowlingSymbols::push);
        }).isInstanceOf(classType);
    }

    private static Stream<Arguments> providePitchesContAndValue() {
        return Stream.of(
                Arguments.of(2, Arrays.asList(4, 6, 7), IllegalArgumentException.class),
                Arguments.of(2, Arrays.asList(5, 5, 5), IllegalArgumentException.class),
                Arguments.of(3, Arrays.asList(10, 10, 11), IllegalArgumentException.class),
                Arguments.of(3, Arrays.asList(10, 10, 10, 10), IllegalArgumentException.class)
        );
    }
}
