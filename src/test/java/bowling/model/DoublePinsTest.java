package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.model.DownPin.DOWN_ALL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class DoublePinsTest {

    @DisplayName("핀을 두개 담는데 성공한다")
    @Test
    void createPins_inputPinTwo_success() {
        // given
        DownPin first = DownPin.DOWN_ZERO;
        DownPin second = DOWN_ALL;

        // when
        DoublePins result = DoublePins.valueOf(first, second);

        // then
        assertThat(result.getDownDownPins()).hasSize(2);
    }

    @DisplayName("핀의 합이 10이상일 경우 실패한다")
    @Test
    void createPins_success() {
        // exception
        assertThatExceptionOfType(InvalidPinException.class)
                .isThrownBy(() -> DoublePins.valueOf(DOWN_ALL, DOWN_ALL));
    }
}