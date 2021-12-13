package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PinTest {

    @Test
    void 볼링_핀을_생성한다() {
        //given
        Pin pin = Pin.from(1);
        //when & then
        assertThat(pin).isEqualTo(Pin.from(1));
    }

    @ValueSource(ints = {0, 11})
    @ParameterizedTest
    void 핀_번호_1_10_사이의_번호가_아니면_IllegalArgumentException_이_발생한다(int number) {
        //given
        //when
        //then
        Assertions.assertThatThrownBy(() -> Pin.from(number))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
