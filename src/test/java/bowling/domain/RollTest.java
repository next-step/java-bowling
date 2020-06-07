package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RollTest {

    @DisplayName("유효한 볼링핀의 범위인지 테스트")
    @ParameterizedTest
    @ValueSource(ints = {11})
    public void testRollNumber(final int pins) {
        assertThatThrownBy(() -> {
            new Roll(pins);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("스트라이크인지 테스트")
    @ParameterizedTest
    @ValueSource(ints = {10})
    public void testStrike(final int pins) {
        Roll roll = new Roll(pins);
        assertThat(roll.isStrike()).isTrue();
    }
}
