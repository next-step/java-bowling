package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StrikeTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 9})
    @DisplayName("FramePins 는 스트라이크인 경우 투구는 반드시 10개이어야 한다.")
    void validate_strike(int countOfPins) {
        assertThatThrownBy(() -> Strike.of(Pins.of(countOfPins)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("스트라이크의 투구는 반드시 " + FramePins.MAX_PINS_PER_FRAME + " 개 입니다.");
    }
}