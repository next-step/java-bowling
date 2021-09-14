package bowling.domain.Pin;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalPinsTest {

    @Test
    @DisplayName("마지막 라운드 핀의 첫번째 핀을 저장할 수 있다.")
    void finalPinFirstTest() {

        // given
        Pin first = Pin.of(6);

        // when
        FinalPins result = FinalPins.ofFirst(first);

        // then
        assertThat(result).isInstanceOf(FinalPins.class);
    }
}