package bowling.domain.frame.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MissTest {
    @DisplayName("조건을 만족하지 않는 핀의 갯수를 넣어줄 때 예외를 던진다.")
    @ParameterizedTest(name = "{displayName} 핀: {0}, {1}")
    @CsvSource({
        "10,0",
        "5,5"
    })
    void createWithInvalidPins(int firstPins, int secondPins) {
        assertThatThrownBy(() -> new Miss(new Pins(firstPins), new Pins(secondPins)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Miss의 조건을 만족하지 않습니다");
    }

    @Test
    @DisplayName("Miss 상태에서는 볼링을 시도할 시 예외를 던진다.")
    void bowl() {
        assertThatThrownBy(() -> new Miss(new Pins(4), new Pins(5)).bowl(10))
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("Miss 상태에서는 해당 프레임의 종료를 알린다.")
    void isFinish() {
        assertThat(new Miss(new Pins(4), new Pins(5)).isFinish()).isTrue();
    }
}
