package bowling;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbol;
import bowling.domain.PointSymbols;
import bowling.domain.state.FrameState;
import bowling.domain.state.FrameStateBonus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameStateBonusTest {
    @Test
    void When_Double_Then_TwoStrikeSymbol() {
        FrameState state = new FrameStateBonus(new Pinfall(10));
        state.roll(new Pinfall(10));
        assertThat(state.pointSymbols()).isEqualTo(new PointSymbols(PointSymbol.STRIKE, PointSymbol.STRIKE));
    }

    @ParameterizedTest
    @MethodSource("providePointSymbolTestSource")
    void Given_Pinfalls_When_PointSymbols_Then_ReturnRightSymbols(List<Pinfall> pinfalls, PointSymbols expected) {
        FrameState state = new FrameStateBonus(pinfalls);

        assertThat(state.pointSymbols()).isEqualTo(expected);
    }

    private static Stream<Arguments> providePointSymbolTestSource() {
        return Stream.of(
                Arguments.of(Arrays.asList(new Pinfall(10)), new PointSymbols(PointSymbol.STRIKE)),
                Arguments.of(Arrays.asList(new Pinfall(10), new Pinfall(10)),
                        new PointSymbols(Arrays.asList(PointSymbol.STRIKE, PointSymbol.STRIKE))),
                Arguments.of(Arrays.asList(new Pinfall(10), new Pinfall(10), new Pinfall(10)),
                        new PointSymbols(Arrays.asList(PointSymbol.STRIKE, PointSymbol.STRIKE, PointSymbol.STRIKE))),
                Arguments.of(Arrays.asList(new Pinfall(1), new Pinfall(9)),
                        new PointSymbols(Arrays.asList(PointSymbol.ONE, PointSymbol.SPARE))),
                Arguments.of(Arrays.asList(new Pinfall(1), new Pinfall(9), new Pinfall(2)),
                        new PointSymbols(Arrays.asList(PointSymbol.ONE, PointSymbol.SPARE, PointSymbol.TWO))),
                Arguments.of(Arrays.asList(new Pinfall(10), new Pinfall(9), new Pinfall(1)),
                        new PointSymbols(Arrays.asList(PointSymbol.STRIKE, PointSymbol.NINE, PointSymbol.SPARE))),
                Arguments.of(Arrays.asList(new Pinfall(10), new Pinfall(2), new Pinfall(1)),
                        new PointSymbols(Arrays.asList(PointSymbol.STRIKE, PointSymbol.TWO, PointSymbol.ONE))),
                Arguments.of(Arrays.asList(new Pinfall(1), new Pinfall(9), new Pinfall(10)),
                        new PointSymbols(Arrays.asList(PointSymbol.ONE, PointSymbol.SPARE, PointSymbol.STRIKE)))
        );
    }
}
