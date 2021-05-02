package bowling.domain.pin;

import bowling.domain.TestFixture;
import bowling.exception.InvalidPinCountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalPinsTest {

    @Test
    @DisplayName("첫 투구가 스트라이크이고, 다음 투구가 일반 투구라면, 마지막 투구는 스페어까지만 허용된다.")
    void strikeAndSpareGame() {
        // given
        final FinalPins finalPins = FinalPins.of(TestFixture.STRIKE_PIN, new Pin(5));

        // when
        // then
        assertThatThrownBy(() -> finalPins.validatePinCount(TestFixture.STRIKE_PIN))
                .isInstanceOf(InvalidPinCountException.class)
                .hasMessage(InvalidPinCountException.INVALID_PIN_COUNT);
    }
}
