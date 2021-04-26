package bowling;

import bowling.domain.Pins;
import bowling.domain.exception.PinsCountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinsTest {
    @Test
    @DisplayName("Spare 테스트")
    void spareTest() {
        Pins firstPins = Pins.ofFirstPitch(7);
        Pins secondPins = firstPins.ofSecondPitch(3);
        assertThat(firstPins.isSpare(secondPins)).isTrue();
    }

    @Test
    @DisplayName("Strike 테스트")
    void strikeTest() {
        assertThat(Pins.ofFirstPitch(10).isStrike()).isTrue();
    }

    @ParameterizedTest(name = "첫번째 넘어뜨린 핀 개수 예외 테스트")
    @ValueSource(ints = {-1,11,13})
    void nameLengthExceptionTest(int pitch) {
        assertThatThrownBy(() -> Pins.ofFirstPitch(pitch))
                .isInstanceOf(PinsCountException.class)
                .hasMessage("넘어뜨린 핀수는 0에서 10 사이의 정수여야 합니다.");
    }

    @ParameterizedTest(name = "두번째까지 넘어뜨린 핀 개수 예외 테스트")
    @ValueSource(ints = {4,5,8})
    void secondPitchExceptionTest(int pitch) {
        Pins pins = Pins.ofFirstPitch(7);
        assertThatThrownBy(() -> pins.ofSecondPitch(pitch))
                .isInstanceOf(PinsCountException.class)
                .hasMessage("넘어뜨린 핀수는 0에서 10 사이의 정수여야 합니다.");
    }
}
