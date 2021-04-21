package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreSymbolTest {

    @DisplayName("핀을 10개를 쓰러뜨리고 첫번째 투구이면 스트라이크로 판단한다")
    @Test
    void strike() {
        ScoreSymbol scoreSymbol = ScoreSymbol.valueOf(10, true);
        assertThat(scoreSymbol).isEqualTo(ScoreSymbol.STRIKE);
    }

    @DisplayName("핀을 10개를 쓰러뜨리고 첫번째 투구가 아니면 스페로 판단한다")
    @Test
    void spare() {
        ScoreSymbol scoreSymbol = ScoreSymbol.valueOf(10, false);
        assertThat(scoreSymbol).isEqualTo(ScoreSymbol.SPARE);
    }

    @DisplayName("핀을 다 쓰러뜨리지 못했을 때 두번째 투구에서 핀을 0개를 쓰러뜨리면 gutter로 판단한다")
    @Test
    void gutter() {
        ScoreSymbol scoreSymbol = ScoreSymbol.valueOf(0, false);
        assertThat(scoreSymbol).isEqualTo(ScoreSymbol.GUTTER);
    }
}
