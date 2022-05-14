package bowling.model.frame.state;

import bowling.model.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

@DisplayName("스트라이크")
class StrikeTest {

    @Test
    @DisplayName("종료 여부는 항상 참")
    void isEnd() {
        assertThat(Strike.INSTANCE.isEnd()).isTrue();
    }

    @Test
    @DisplayName("다음 상태는 생성 불가")
    void state_thrownIllegalStateException() {
        assertThatIllegalStateException().isThrownBy(() -> Strike.INSTANCE.state(Pins.ZERO));
    }

    @Test
    @DisplayName("남은 투구는 2")
    void remainCount() {
        assertThat(Strike.INSTANCE.remainCount()).isEqualTo(2);
    }
}
