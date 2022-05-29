package bowling.domain.state;

import bowling.domain.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StrikeTest {
    @DisplayName("Strike 생성한다.")
    @Test
    void Strike_생성() {
        assertThat(new Strike()).isNotNull().isInstanceOf(Strike.class);
    }

    @DisplayName("Strike 상태는 종료 상태이므로 투구시 예외가 발생한다.")
    @Test
    void bowl_투구_예외() {
        Strike strike = new Strike();
        Pins pins = new Pins(3);
        assertThatThrownBy(() -> strike.bowl(pins)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Strike 상태는 프레임 종료이므로 true 를 반환한다.")
    @Test
    void isFrameEnd_종료_상태_체크() {
        Strike strike = new Strike();
        assertThat(strike.isEnd()).isTrue();
    }

    @DisplayName("Spare 상태는 쓰러트린 핀이 0일 경우 '-' 기호를 반환하고, 1 ~ 9개를 쓰러트릴 경우 쓰러트린 숫자를 기호로 반환한다.")
    @Test
    void symbol_기호_체크() {
        assertThat(new Strike().symbol()).isEqualTo("X");
    }
}