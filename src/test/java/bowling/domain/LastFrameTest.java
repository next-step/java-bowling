package bowling.domain;

import bowling.exception.CannotAddPinsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LastFrameTest {

    private LastFrame frame;

    @BeforeEach
    void setUp() {
        frame = new LastFrame();
    }

    @DisplayName("스트라이크")
    @Test
    void strike() {
        frame.bowl(10);
        assertThat(frame.results()).containsExactly(PitchResult.of(10));
    }

    @DisplayName("스페어")
    @Test
    void spare() {
        frame.bowl(1);
        frame.bowl(9);
        assertThat(frame.results()).containsExactly(PitchResult.of(1), PitchResult.spare(9));
    }

    @DisplayName("미스")
    @Test
    void miss() {
        frame.bowl(1);
        frame.bowl(4);
        assertThat(frame.results()).containsExactly(PitchResult.of(1), PitchResult.of(4));
    }

    @DisplayName("거터")
    @Test
    void gutter() {
        frame.bowl(0);
        assertThat(frame.results()).containsExactly(PitchResult.of(0));
    }

    @DisplayName("두 번째 투구 결과, 쓰러진 핀의 총합은 10을 넘을 수 없다.")
    @Test
    void invalidTotalPins() {
        frame.bowl(7);
        assertThatThrownBy(() -> frame.bowl(4))
                .isInstanceOf(CannotAddPinsException.class);
    }

    @DisplayName("두 번의 투구 내에 스트라이크 또는 스페어를 치지 못한 경우, 프레임은 종료된다.")
    @Test
    void noBonus() {
        frame.bowl(4);
        frame.bowl(5);
        assertThat(frame.isEnd()).isTrue();
    }

    @DisplayName("두 번의 투구 내에 스트라이크를 친 경우, 프레임은 종료되지 않는다.")
    @Test
    void bonusAfterStrike() {
        frame.bowl(10);
        assertThat(frame.isEnd()).isFalse();
    }

    @DisplayName("두 번의 투구 내에 스페어를 친 경우, 프레임은 종료되지 않는다.")
    @Test
    void bonusAfterSpare() {
        frame.bowl(4);
        frame.bowl(6);
        assertThat(frame.isEnd()).isFalse();
    }

    @DisplayName("두 번의 투구 내에 스트라이크를 친 경우, 보너스 투구를 던질 수 있다.")
    @Test
    void bonusBowlAfterStrike() {
        frame.bowl(10);
        frame.bowl(4);
        frame.bowl(6);
        assertThat(frame.results()).containsExactly(PitchResult.of(10), PitchResult.of(4), PitchResult.of(6));
    }

    @DisplayName("두 번의 투구 내에 스페어를 친 경우, 보너스 투구를 던질 수 있다.")
    @Test
    void bonusBowlAfterSpare() {
        frame.bowl(4);
        frame.bowl(6);
        frame.bowl(10);
        assertThat(frame.results()).containsExactly(PitchResult.of(4), PitchResult.spare(6), PitchResult.of(10));
    }

}
