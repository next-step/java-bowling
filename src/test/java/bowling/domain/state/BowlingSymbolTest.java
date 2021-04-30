package bowling.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BowlingSymbolTest {

    @Test
    void 단일_볼링핀_출력_테스트() {
        // given
        String symbol = BowlingSymbol.of(BowlingPin.of(5));
        // when & then
        assertThat(symbol).isEqualTo("5");
    }

    @Test
    void 볼링핀_출력_테스트() {
        // given
        String symbol = BowlingSymbol.of(BowlingPin.of(5), BowlingPin.of(3));
        // when & then
        assertThat(symbol).isEqualTo("5|3");
    }

    @Test
    void 거터_출력_테스트() {
        // given
        String symbol = BowlingSymbol.of(BowlingPin.of(5), BowlingPin.of(0));
        // when & then
        assertThat(symbol).isEqualTo("5|-");
    }

    @Test
    void 스트라이크_출력_테스트() {
        // given
        String symbol = BowlingSymbol.of(BowlingPin.of(10));
        // when & then
        assertThat(symbol).isEqualTo("X");
    }

    @Test
    void 스페어_출력_테스트() {
        // given
        String symbol = BowlingSymbol.of(BowlingPin.of(3), BowlingPin.of(7));
        // when & then
        assertThat(symbol).isEqualTo("3|/");
    }
}
