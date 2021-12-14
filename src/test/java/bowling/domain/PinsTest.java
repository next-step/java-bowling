package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest
    @CsvSource(value = {"0:10", "1:9", "2:8", "3:7", "4:6", "5:5",
            "6:4", "7:3", "8:2", "9:1", "10:0"}, delimiter = ':')
    void 핀_n개를_쓰러트린다(int fallDownCount, int existCount) {
        //given
        Pins pins = Pins.create();
        //when
        int result = pins.fallDown(fallDownCount);
        //then
        assertThat(result).isEqualTo(existCount);
    }

    @Test
    void 핀_10개_초과로_쓰러트리면_IllegalArgumentException_이_발생한다() {
        //given
        Pins pins = Pins.create();
        //when
        //then
        assertThatThrownBy(() -> pins.fallDown(11))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
