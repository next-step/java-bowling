package bowling.domain.value;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinsTest {

    @Test
    void createTest() {
        Pins pins = new Pins(3);

        assertThat(pins.getPins()).isEqualTo(3);
    }

    @Test
    @DisplayName("볼링핀의 입력 범위 예외 검증")
    void exceptionTest() {
        assertThatThrownBy(() ->
                new Pins(100)).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() ->
                new Pins(-6)).isInstanceOf(IllegalArgumentException.class);
    }
}
