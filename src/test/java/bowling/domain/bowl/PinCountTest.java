package bowling.domain.bowl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinCountTest {

    @ParameterizedTest
    @ValueSource(ints = {PinCount.MIN - 1, PinCount.MAX + 1})
    void PinCount는_범위_밖_값으로_생성_될_경우_예외를_발생_시킨다(int pinCount) {
        assertThatThrownBy(() -> {
            new PinCount(pinCount);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
