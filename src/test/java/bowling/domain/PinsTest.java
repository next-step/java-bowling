package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PinsTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    @DisplayName("핀 생성 성공")
    void create(int count) {
        assertThat(Pins.of(count)).isInstanceOf(Pins.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2})
    @DisplayName("핀 수가 최소 수 보다 작을 경우")
    void minimumException(int count) {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Pins.of(count));
    }

    @ParameterizedTest
    @ValueSource(ints = {11, 12, 13})
    @DisplayName("핀 수가 최소 수 보다 작을 경우")
    void maximumException(int count) {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Pins.of(count));
    }

}
