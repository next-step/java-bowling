package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinsTest {

    @Test
    void 볼링_핀_10개를_생성한다() {
        //given
        Pins pins = Pins.create();
        //when
        //then
        assertThat(pins.size()).isEqualTo(10);
    }

    @Test
    void 볼링_핀_10개가_생성되지_않으면_IllegalArgumentException_이_발생한다() {
        //given
        //when
        //then
        assertThatThrownBy(() -> Pins.create(9))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
