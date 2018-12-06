package domain;

import exception.PinBoundOverException;
import org.junit.Test;
import utils.StringUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class PinTest {
    @Test(expected = PinBoundOverException.class)
    public void 개수넘을경우() {
        Pin.of(11);
    }

    @Test(expected = PinBoundOverException.class)
    public void 음수일경우() {
        Pin.of(-1);
    }

    @Test
    public void 스트라이크() {
        Pin pin = Pin.of(10);
        assertThat(pin.toString()).isEqualTo(StringUtils.STRIKE);
    }

    @Test
    public void 거터() {
        Pin pin = Pin.of(0);
        assertThat(pin.toString()).isEqualTo(StringUtils.GUTTER);
    }

    @Test
    public void 넥스트핀() {
        Pin pin = Pin.of(0);
        assertThat(pin.toString()).isEqualTo(StringUtils.GUTTER);
    }
}