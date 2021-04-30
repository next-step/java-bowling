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
}
