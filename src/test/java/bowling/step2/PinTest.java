package bowling.step2;

import bowling.step2.domain.Pin;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinTest {

    @Test
    public void 핀_생성() {
        //given, when
        Pin pin = Pin.of(10);

        //then
        assertThat(pin.getCount()).isEqualTo(10);
    }

    @Test
    public void 핀_생성_실패() {
        //given, when, then
        assertThatThrownBy(() -> {
            Pin pin = Pin.of(11);
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void 다음_핀_생성() {
        //given
        Pin pin = Pin.of(3);

        //when
        Pin nextPitch = pin.nextPitch(3);

        //then
        assertThat(nextPitch.getCount()).isEqualTo(3);
    }

    @Test
    public void 다음_핀_생성2() {
        //given
        Pin pin = Pin.of(3);
        //when, then
        assertThatThrownBy(() -> {
            Pin nextPitch = pin.nextPitch(10);
        }).isInstanceOf(RuntimeException.class);
    }
}
