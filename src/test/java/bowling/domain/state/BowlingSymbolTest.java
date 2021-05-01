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
    void 거터_출력_테스트() {
        // given
        String symbol = BowlingSymbol.of(BowlingPin.of(0));
        // when & then
        assertThat(symbol).isEqualTo("-");
    }

    @Test
    void 스트라이크_출력_테스트() {
        // given
        String symbol = BowlingSymbol.ofStrike();
        // when & then
        assertThat(symbol).isEqualTo("X");
    }

    @Test
    void 스페어_출력_테스트() {
        // given
        String symbol = BowlingSymbol.ofSpare();
        // when & then
        assertThat(symbol).isEqualTo("/");
    }
}
