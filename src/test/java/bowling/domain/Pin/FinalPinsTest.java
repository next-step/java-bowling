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

    @Test
    @DisplayName("마지막 프레임 핀의 두번째 핀을 저장할 수 있다.")
    void finalPinNextTest() {

        // given
        Pin first = Pin.of(6);
        FinalPins pins = FinalPins.ofFirst(first);
        Pin next = Pin.of(4);

        // when
        FinalPins result = pins.ofNext(next);

        // then
        assertThat(result).isInstanceOf(FinalPins.class);
    }

    @Test
    @DisplayName("마지막 프레임 핀의 마지막 핀을 저장할 수 있다.")
    void finalPinLastTest() {

        // given
        Pin first = Pin.of(6);
        FinalPins firstPins = FinalPins.ofFirst(first);
        Pin next = Pin.of(4);
        FinalPins secondPins = firstPins.ofNext(next);
        Pin last = Pin.of(5);

        // when
        FinalPins result = secondPins.ofNext(last);

        // then
        assertThat(result).isInstanceOf(FinalPins.class);
    }

}