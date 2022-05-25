package bowling.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StrikeTest {
    @Test
    @DisplayName("스트라이크에서 같은 프레임에 투구를 하는 경우, 예외처리를 한다")
    void bowl() {
        // given
        Strike strike = new Strike();
        Pitching pitching = new Pitching(5);

        // when & then
        assertThatThrownBy(() -> strike.bowl(pitching))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("스트라이크의 경우 프레임 종료가 True인지 확인한다")
    void checkedStrikeStateIsEnd() {
        // given
        Strike strike = new Strike();

        // when & then
        assertThat(strike.isEnd()).isTrue();
    }

    @Test
    @DisplayName("스트라이크의 경우 symbol이 X인지 확인한다")
    void checkedStrikeSymbolIsX() {
        // given
        Strike strike = new Strike();

        // when
        String symbol = strike.symbol();

        // then
        assertThat(symbol).isEqualTo("X");
    }
}