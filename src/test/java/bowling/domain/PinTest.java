package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PinTest {
    @Test
    void constructor() {
        assertThat(Pin.from()).isNotNull();
    }

    @Test
    void hit_strike() {
        Pin pin = Pin.from();
        assertThat(pin.hit(10)).isEqualTo("X");
    }

    @Test
    void hit_spare() {
        Pin pin = Pin.from();
        assertThat(pin.hit(8)).isEqualTo("8");
        assertThat(pin.hit(2)).isEqualTo("/");
    }

    @Test
    void hit_miss() {
        Pin pin = Pin.from();
        assertThat(pin.hit(1)).isEqualTo("1");
        assertThat(pin.hit(2)).isEqualTo("2");
    }

    @Test
    void hit_gutter() {
        Pin pin = Pin.from();
        assertThat(pin.hit(0)).isEqualTo("-");
        assertThat(pin.hit(2)).isEqualTo("2");
    }

    @Test
    void isClear() {
        Pin pin = Pin.from();
        pin.hit(1);
        assertThat(pin.isClear()).isFalse();
        pin.hit(9);

        assertThat(pin.isClear()).isTrue();
    }

    @Test
    void canHit() {
        Pin pin = Pin.from();
        pin.hit(1);
        assertThat(pin.catHit()).isTrue();
        pin.hit(9);

        assertThat(pin.catHit()).isFalse();
    }

    @Test
    void canHit_strike() {
        Pin pin = Pin.from();
        pin.hit(10);

        assertThat(pin.catHit()).isFalse();
    }

    @Test
    void canHit_spare() {
        Pin pin = Pin.from();
        pin.hit(1);
        pin.hit(9);

        assertThat(pin.catHit()).isFalse();
    }
}
