package bowling;

import bowling.domain.KnockedPinCount;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KnockedPinCountTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 10})
    void 쓰러뜨린_핀의_개수는_0에서_10개_동등성가짐(int count) {
        assertThat(new KnockedPinCount(count)).isEqualTo(new KnockedPinCount(count));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void 쓰러뜨린_핀의_개수는_음수거나_11개이상일수없다_예외발생(int count) {
        assertThatThrownBy(() -> new KnockedPinCount(count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(KnockedPinCount.INVALID_KNOCK_OUT_COUNT_MESSAGE);
    }
}
