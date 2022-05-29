package bowling.domain.state;

import bowling.domain.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SpareTest {
    @DisplayName("Spare 생성한다.")
    @Test
    void Spare_생성() {
        assertThat(new Spare(new Pins(3))).isNotNull().isInstanceOf(Spare.class);
    }

    @DisplayName("Spare 생성 시 첫번째 투구에서 10개의 핀을 쓰러트릴 경우 예외가 발생한다.")
    @Test
    void Spare_생성_예외() {
        Pins firstPins = new Pins(10);
        assertThatThrownBy(() -> new Spare(firstPins)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Spare 상태는 종료 상태이므로 투구시 예외가 발생한다.")
    @Test
    void bowl_투구_예외() {
        Spare spare = new Spare(new Pins(3));
        Pins pins = new Pins(3);
        assertThatThrownBy(() -> spare.bowl(pins)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Spare 상태는 프레임 종료이므로 true 를 반환한다.")
    @Test
    void isFrameEnd_종료_상태_체크() {
        Spare spare = new Spare(new Pins(3));
        assertThat(spare.isEnd()).isTrue();
    }

    @DisplayName("Spare 상태는 쓰러트린 핀이 0일 경우 '-' 기호를 반환하고, 1 ~ 9개를 쓰러트릴 경우 쓰러트린 숫자를 기호로 반환한다. 2번째 투구 기호는 '/' 기호를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "0, -|/",
            "1, 1|/",
            "8, 8|/"
    })
    void symbol_기호_체크(int firstPins, String symbol) {
        assertThat(new Spare(new Pins(firstPins)).symbol()).isEqualTo(symbol);
    }
}