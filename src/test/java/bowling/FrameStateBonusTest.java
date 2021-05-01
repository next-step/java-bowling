package bowling;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbol;
import bowling.domain.PointSymbols;
import bowling.domain.Score;
import bowling.domain.state.FrameState;
import bowling.domain.state.FrameStateBonus;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameStateBonusTest {
    @ParameterizedTest
    @MethodSource("providePointSymbolTestSource")
    void Given_Pinfalls_When_PointSymbols_Then_ReturnRightSymbols(List<Pinfall> pinfalls, PointSymbols expected, Score expectedScore) {
        FrameState state = new FrameStateBonus(pinfalls);

        assertThat(state.pointSymbols()).isEqualTo(expected);
        assertThat(state.score()).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> providePointSymbolTestSource() {
        return Stream.of(
                Arguments.of(Arrays.asList(new Pinfall(10)), new PointSymbols(PointSymbol.STRIKE), Score.createNotDetermined()),
                Arguments.of(Arrays.asList(new Pinfall(10), new Pinfall(10)),
                        new PointSymbols(Arrays.asList(PointSymbol.STRIKE, PointSymbol.STRIKE)), Score.createNotDetermined()),
                Arguments.of(Arrays.asList(new Pinfall(10), new Pinfall(10), new Pinfall(10)),
                        new PointSymbols(Arrays.asList(PointSymbol.STRIKE, PointSymbol.STRIKE, PointSymbol.STRIKE)), Score.create(30)),
                Arguments.of(Arrays.asList(new Pinfall(1), new Pinfall(9)),
                        new PointSymbols(Arrays.asList(PointSymbol.ONE, PointSymbol.SPARE)), Score.createNotDetermined()),
                Arguments.of(Arrays.asList(new Pinfall(1), new Pinfall(9), new Pinfall(2)),
                        new PointSymbols(Arrays.asList(PointSymbol.ONE, PointSymbol.SPARE, PointSymbol.TWO)), Score.create(12)),
                Arguments.of(Arrays.asList(new Pinfall(10), new Pinfall(9), new Pinfall(1)),
                        new PointSymbols(Arrays.asList(PointSymbol.STRIKE, PointSymbol.NINE, PointSymbol.SPARE)), Score.create(20)),
                Arguments.of(Arrays.asList(new Pinfall(10), new Pinfall(2), new Pinfall(1)),
                        new PointSymbols(Arrays.asList(PointSymbol.STRIKE, PointSymbol.TWO, PointSymbol.ONE)), Score.create(13)),
                Arguments.of(Arrays.asList(new Pinfall(1), new Pinfall(9), new Pinfall(10)),
                        new PointSymbols(Arrays.asList(PointSymbol.ONE, PointSymbol.SPARE, PointSymbol.STRIKE)), Score.create(20))
        );
    }

    @Test
    @Disabled
    void When_Pinfalls_Then_10() {
//        AssertionsForInterfaceTypes.assertThat(new FrameStateStrike().pinfalls()).isEqualTo(Arrays.asList(new Pinfall(10)));
    }
}
