package bowling.domain.state.end;

import bowling.domain.Pins;
import bowling.domain.state.ThrowingState;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StrikeTest {

    @Test
    void 예외_재투구_불가() {
        // given
        ThrowingState state = new Strike();

        // when
        assertThatThrownBy(() -> state.bowl(new Pins(1)))
                // then
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("프레임이 끝난 상태는 투구할 수 없습니다.");
    }

    @Test
    void 투구_끝_여부_확인() {
        ThrowingState state = new Strike();
        assertThat(state.isEnd()).isTrue();
    }

    @Test
    void 심볼_확인() {
        ThrowingState state = new Strike();
        assertThat(state.symbol()).isEqualTo("X");
    }
}