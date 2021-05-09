package bowling.domain;

import bowling.domain.rollresult.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinTest {
    public static final Pin PIN = Pin.of();
    public static final Pin STRIKE_PIN = Pin.of(1, 0);
    public static final Pin SPARE_PIN = Pin.of(2, 0);
    public static final Pin MISS_PIN = Pin.of(2, 10);
    public static final Pin GUTTER_PIN = Pin.of(1, 10);
    public static final Pin THREE_HIT_PIN = Pin.of(1, 7);
    public static final Pin THIRD_THREE_HIT_PIN = Pin.of(3, 7);

    public Pin pin;

    @BeforeEach
    void setUp() {
        pin = Pin.of();
    }

    @Test
    void 핀을생성() {
        assertThat(PIN).isEqualTo(Pin.of(0, 10));
    }

    @Test
    void 시도횟수는_0과3사이() {
        assertThatThrownBy(() -> {
            Pin.of(4, 10);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 핀의수는_0과10사이() {
        assertThatThrownBy(() -> {
            Pin.of(3, 11);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void gutter핀의_결과확인() {
        RollResultType type = pin.firstHit(HitNumber.of(0));
        assertThat(type).isEqualTo(Gutter.of());
    }

    @Test
    void strike핀의_결과확인() {
        assertThat(pin.firstHit(HitNumber.of(10))).isEqualTo(Strike.of());
    }

    @Test
    void spare핀의_결과확인() {
        RollResultType type = pin.firstHit(HitNumber.of(7));
        assertThat(pin.nextHit(type, HitNumber.of(3))).isEqualTo(Spare.of(7));
    }

    @Test
    void miss핀의_결과확인() {
        RollResultType type = pin.firstHit(HitNumber.of(0));
        RollResultType next = pin.nextHit(type, HitNumber.of(3));
        assertThat(next).isEqualTo(Miss.of(0, 3));
    }
}
