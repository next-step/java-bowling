package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DownPinTest {

    @Test
    @DisplayName(value = "쓰러트린 핀 생성")
    void createDownPin() {
        DownPin downPin = new DownPin(1);

        assertThat(new DownPin(1))
                .isEqualTo(downPin);
    }

    @ParameterizedTest
    @DisplayName(value = "쓰러트린 핀 예외")
    @ValueSource(ints = {-1, 11})
    void downPin_exception(int input) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    new DownPin(input);
                }).withMessageMatching("쓰러트린 볼링 핀의 개수는 0과 10사이의 숫자여야 합니다.");
    }
}
