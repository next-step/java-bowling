package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PinTest {
    private final int FIRST_POINT = 2;
    private final int SECOND_POINT = 3;
    private final int SPARE_POINT = 8;
    private final int STRIKE_POINT = 10;

    private Pin pin;

    @BeforeEach
    void setUp() {
        pin = Pin.of(FIRST_POINT);
    }

    @Test
    void 정적_팩토리_메서드_테스트() {
        Pin pin_2 = Pin.of(FIRST_POINT);
        assertThat(pin.equals(pin_2)).isTrue();
    }

    @Test
    void 스트라이크_테스트() {
        assertThat(Pin.of(STRIKE_POINT).isStrike()).isTrue();
    }

    @Test
    void 스페어_테스트() {
        assertThat(Pin.of(FIRST_POINT).isSpare(Pin.of(SPARE_POINT))).isTrue();
    }

    @Test
    void 미스_테스트() {
        assertThat(Pin.of(FIRST_POINT).isMiss(Pin.of(SECOND_POINT))).isTrue();
    }

    @Test
    void 숫자범위_외_입력_예외발생_1() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Pin.of(-1);
        });
    }

    @Test
    void 숫자범위_외_입력_예외발생_2() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Pin.of(11);
        });
    }
}
