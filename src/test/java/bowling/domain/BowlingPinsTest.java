package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BowlingPinsTest {

    @Test
    @DisplayName("볼링 핀 객체 생성")
    void create() {
        BowlingPins pins = new BowlingPins(10);
        assertThat(pins).isEqualTo(new BowlingPins(10));
    }

    @ParameterizedTest
    @DisplayName("볼링 핀 검증 0-10 아닐 경우 -> IllegalArgumentException 반환")
    @ValueSource(ints = {-1, 11})
    void invalid(int pin) {
        assertThatThrownBy(() -> new BowlingPins(pin))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
