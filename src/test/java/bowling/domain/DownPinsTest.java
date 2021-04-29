package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DownPinsTest {
    @Test
    @DisplayName(value = "쓰러트린 핀 개수 추가")
    void downPins_add() {
        DownPins downPins = new DownPins();

        downPins.add(1);

        assertThat(downPins.size())
                .isEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName(value = "쓰러트린 핀 개수 합계")
    @CsvSource(value = {"5:10", "2:4"}, delimiter = ':')
    void downPins_sum(int input, int result) {
        DownPins downPins = new DownPins();

        downPins.add(input);
        downPins.add(input);

        assertThat(downPins.sum())
                .isEqualTo(result);
    }
}
