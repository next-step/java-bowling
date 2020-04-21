package bowling.domain.pin;

import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PinsTest {

    @DisplayName("1,2회 투구 결과의 합니 10개를 넘을 수 없다")
    @Test
    public void validate_fail() throws Exception {
        assertThatThrownBy(
                () -> new Pins(new Pin(10), new Pin(10))
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("쓰러진 핀의 개수를 가져온다")
    @Test
    public void getDownPins_success() throws Exception {
        //given
        Pins pins = new Pins(new Pin(2), new Pin(3));

        //when
        int downPins = pins.getDownPins();

        //then
        assertThat(downPins).isEqualTo(5);
    }

    @DisplayName("쓰러진 핀의 개수를 가져온다 : 2째 투구가 null 일때")
    @Test
    public void getDownPins_success_secondNull() throws Exception {
        //given
        Pins pins = new Pins(new Pin(2), null);

        //when
        int downPins = pins.getDownPins();

        //then
        assertThat(downPins).isEqualTo(2);
    }

    @DisplayName("전체 투구의 합이 10이면 true")
    @Test
    public void isFinish_success() throws Exception {
        //given
        Pins pins = new Pins(new Pin(3), new Pin(7));

        //then
        assertTrue(pins.isFinish());
    }

    @DisplayName("strike 상태 체크")
    @Test
    public void isStrike_success() throws Exception {
        //given
        Pins pins = new Pins(new Pin(10), null);

        //then
        assertTrue(pins.isStrike());
    }

    @DisplayName("spare 상태 체크")
    @Test
    public void isSpare_success() throws Exception {
        //given
        Pins pins = new Pins(new Pin(9), new Pin(1));

        //then
        assertTrue(pins.isSpare());
    }

    @DisplayName("gutter 상태 체크")
    @Test
    public void isGutter_success() throws Exception {
        //given
        Pins pins = new Pins(new Pin(0), new Pin(0));

        //then
        assertTrue(pins.isGutter());
    }

    @Test
    public void addSecondPin() throws Exception {
        //given
        Pin add = new Pin(2);
        Pins pins = new Pins(new Pin(3), null);
        Pins compare = new Pins(new Pin(3), add);

        //when
        pins = pins.addSecondPin(add);

        //then
        assertTrue(pins.equals(compare));
    }
}
