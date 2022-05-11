package bowling.model;

import bowling.exception.InvalidPinsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinsTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    @DisplayName("핀을 정상적으로 생성한다")
    void create(int number) {
        assertThat(Pins.create(number)).isInstanceOf(Pins.class);
    }

    @Test
    @DisplayName("올바르지 않은 핀을 입력할 경우 예외를 발생시킨다")
    void validate() {
        Assertions.assertAll(
                () -> assertThatThrownBy(() -> Pins.create(-1)).isInstanceOf(InvalidPinsException.class),
                () -> assertThatThrownBy(() -> Pins.create(11)).isInstanceOf(InvalidPinsException.class)
        );
    }

}