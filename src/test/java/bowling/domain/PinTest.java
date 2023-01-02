package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinTest {

    @Test
    void 생성_0개_미만() {
        assertThatThrownBy(() -> new Pin(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 생성_10개_초과() {
        assertThatThrownBy(() -> new Pin(11))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"9,false", "10,true"})
    void 볼링_핀이_최대값을_가지는지_확인(int amount, boolean expected) {
        assertThat(new Pin(amount).isMax()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"5,5,true", "0,10,true", "0,0,false", "0,9,false"})
    void 볼링_핀의_합계가_최대값이_되는지_확인(int firstPinAmount, int secondPinAmount, boolean expected) {
        assertThat(new Pin(firstPinAmount).isClear(new Pin(secondPinAmount))).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"0, -", "1, 1", "10, 10"})
    void 볼링_핀_개수_출력(int amount, String expected) {
        assertThat(new Pin(amount).toString()).isEqualTo(expected);
    }
}