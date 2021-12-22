package bowling.domain;

import bowling.domain.pin.Pins;
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
    void 볼링_핀_10개_초과하여_생성되면_IllegalArgumentException_이_발생한다() {
        //given
        //when
        //then
        assertThatThrownBy(() -> Pins.create(11))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"0:10", "1:9", "2:8", "3:7", "4:6", "5:5",
            "6:4", "7:3", "8:2", "9:1", "10:0"}, delimiter = ':')
    void 핀_n개를_쓰러트린다(int fallDownCount, int existCount) {
        //given
        Pins pins = Pins.create();
        //when
        Pins existPins = pins.fallDown(Pins.create(fallDownCount));
        //then
        assertThat(existPins.size()).isEqualTo(existCount);
    }

    @Test
    void 핀_10개_초과로_쓰러트리면_IllegalArgumentException_이_발생한다() {
        //given
        Pins pins = Pins.create();
        //when
        //then
        assertThatThrownBy(() -> pins.fallDown(Pins.create(11)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 스트라이크() {
        //given
        //when
        //then
        assertThat(Pins.create(10).isStrike()).isTrue();
        assertThat(Pins.create(10).isStrike(Pins.create(10))).isTrue();
    }

    @Test
    void 스페어() {
        //given
        //when
        //then
        assertThat(Pins.create(0).isSpare(Pins.create(10))).isTrue();
        assertThat(Pins.create(1).isSpare(Pins.create(9))).isTrue();
        assertThat(Pins.create(5).isSpare(Pins.create(5))).isTrue();
    }

    @Test
    void 미스() {
        //given
        //when
        //then
        assertThat(Pins.create(0).isMiss(Pins.create(9))).isTrue();
        assertThat(Pins.create(1).isMiss(Pins.create(1))).isTrue();
        assertThat(Pins.create(9).isMiss(Pins.create(0))).isTrue();
    }

    @Test
    void 거터() {
        //given
        //when
        //then
        assertThat(Pins.create(0).isGutter()).isTrue();
    }
}
