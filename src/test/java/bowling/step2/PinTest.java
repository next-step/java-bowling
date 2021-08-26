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
        assertThat(pin.count()).isEqualTo(10);
    }

    @Test
    public void 핀_생성_실패() {
        //given, when, then
        assertThatThrownBy(() -> {
            Pin pin = Pin.of(11);
        }).isInstanceOf(RuntimeException.class);
    }
}
