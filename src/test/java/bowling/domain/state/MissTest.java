package bowling.domain.state;

import bowling.domain.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MissTest {
    @DisplayName("Miss 생성한다.")
    @Test
    void Miss_생성() {
        assertThat(new Miss(new Pins(3), new Pins(4))).isNotNull().isInstanceOf(Miss.class);
    }

    @DisplayName("첫번째 투구와 두번째 투구에서 쓰러트린 핀이 10을 넘어서는 경우 예외가 발생한다.")
    @Test
    void Miss_생성_예외() {
        Pins firstPins = new Pins(3);
        Pins secondPins = new Pins(8);
        assertThatThrownBy(() -> new Miss(firstPins, secondPins)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Miss 상태는 종료 상태이므로 투구시 예외가 발생한다.")
    @Test
    void bowl_투구_예외() {
        Miss miss = new Miss(new Pins(3), new Pins(4));
        Pins pins = new Pins(3);
        assertThatThrownBy(() -> miss.bowl(pins)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Miss 상태는 프레임 종료이므로 true 를 반환한다.")
    @Test
    void isFrameEnd_종료_상태_체크() {
        Miss miss = new Miss(new Pins(3), new Pins(4));
        assertThat(miss.isFrameEnd()).isTrue();
    }

    @DisplayName("Miss 상태는 쓰러트린 핀이 0일 경우 '-' 기호를 반환하고, 1 ~ 9개를 쓰러트릴 경우 쓰러트린 숫자를 기호로 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "0, 1, -|1",
            "1, 4, 1|4",
            "8, 0, 8|-",
            "0, 0, -|-"
    })
    void symbol_기호_체크(int firstPins, int secondPins, String symbol) {
        assertThat(new Miss(new Pins(firstPins), new Pins(secondPins)).symbol()).isEqualTo(symbol);
    }
}