package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class PinTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    @DisplayName("핀의 개수를 입력받아 핀을 생성한다.")
    void create(final int pinCount) {
        // given
        // when
        final Pin pin = new Pin(pinCount);

        // then
        assertThat(pin).isEqualTo(new Pin(pinCount));
    }
}
