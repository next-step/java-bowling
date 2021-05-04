package bowling;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbol;
import bowling.domain.PointSymbols;
import bowling.domain.Score;
import bowling.domain.state.FrameState;
import bowling.domain.state.FrameStateBonus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameStateBonusTest {
    @DisplayName("보너스 상태에서 쓰러진 핀의 개수에 따라 PontSymbol, 점수, Pinfall이 잘 되는지 테스트")
    @ParameterizedTest
    @MethodSource("providePointSymbolTestSource")
    void Given_Pinfalls_When_PointSymbols_Then_ReturnRightSymbols(List<Pinfall> pinfalls, PointSymbols expectedPointSymbols, Score expectedScore) {
        FrameState state = new FrameStateBonus(pinfalls);

        assertThat(state.pointSymbols()).isEqualTo(expectedPointSymbols);
        assertThat(state.score()).isEqualTo(expectedScore);
        assertThat(state.pinfalls()).isEqualTo(pinfalls);
    }

    private static Stream<Arguments> providePointSymbolTestSource() {
        return Stream.of(
                Arguments.of(Arrays.asList(Pinfall.createStrike()), new PointSymbols(PointSymbol.STRIKE), Score.createNotDetermined()),
                Arguments.of(Arrays.asList(Pinfall.createStrike(), Pinfall.createStrike()),
                        new PointSymbols(Arrays.asList(PointSymbol.STRIKE, PointSymbol.STRIKE)), Score.createNotDetermined()),
                Arguments.of(Arrays.asList(Pinfall.createStrike(), Pinfall.createStrike(), Pinfall.createStrike()),
                        new PointSymbols(Arrays.asList(PointSymbol.STRIKE, PointSymbol.STRIKE, PointSymbol.STRIKE)), Score.create(30)),
                Arguments.of(Arrays.asList(Pinfall.create(1), Pinfall.create(9)),
                        new PointSymbols(Arrays.asList(PointSymbol.ONE, PointSymbol.SPARE)), Score.createNotDetermined()),
                Arguments.of(Arrays.asList(Pinfall.create(1), Pinfall.create(9), Pinfall.create(2)),
                        new PointSymbols(Arrays.asList(PointSymbol.ONE, PointSymbol.SPARE, PointSymbol.TWO)), Score.create(12)),
                Arguments.of(Arrays.asList(Pinfall.createStrike(), Pinfall.create(9), Pinfall.create(1)),
                        new PointSymbols(Arrays.asList(PointSymbol.STRIKE, PointSymbol.NINE, PointSymbol.SPARE)), Score.create(20)),
                Arguments.of(Arrays.asList(Pinfall.createStrike(), Pinfall.create(2), Pinfall.create(1)),
                        new PointSymbols(Arrays.asList(PointSymbol.STRIKE, PointSymbol.TWO, PointSymbol.ONE)), Score.create(13)),
                Arguments.of(Arrays.asList(Pinfall.create(1), Pinfall.create(9), Pinfall.createStrike()),
                        new PointSymbols(Arrays.asList(PointSymbol.ONE, PointSymbol.SPARE, PointSymbol.STRIKE)), Score.create(20))
        );
    }
}
