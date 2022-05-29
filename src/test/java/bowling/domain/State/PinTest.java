package bowling.domain.State;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PinTest {
    public static final Pin ZERO = new Pin(0);
    public static final Pin ONE = new Pin(1);
    public static final Pin NINE = new Pin(9);
    public static final Pin TEN = new Pin(10);

    @ParameterizedTest
    @ValueSource(ints = {Pin.MIN - 1, Pin.MAX + 1})
    void PinCount는_범위_밖_값으로_생성_될_경우_예외를_발생_시킨다(int pinCount) {
        assertThatThrownBy(() -> {
            new Pin(pinCount);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isZero는_0개_여부를_반환한다() {
        assertTrue(ZERO.isZero());
    }

    @Test
    void isTen은_10개_여부를_반환한다() {
        assertTrue(TEN.isTen());
    }
}
