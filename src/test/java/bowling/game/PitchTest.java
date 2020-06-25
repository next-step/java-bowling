package bowling.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PitchTest {

    @DisplayName("쓰러진 핀의 갯수가 0 ~ 10 사이가 아니면 IllegalArguments Exception throw")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void validatePinCount(int pinCount) {
        assertThatThrownBy(() -> new Pitch(pinCount, 10 - pinCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("쓰러진 핀의 갯수는 0 ~ 10 사이어야 합니다. - " + pinCount);
    }

    @DisplayName("남은 핀의 개수가 0 ~ 10 사이가 아니면 IllegalArgumentsException throw")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void validateLeftPinCount(int leftPinCount) {
        assertThatThrownBy(() -> new Pitch(3, leftPinCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("남은 핀의 갯수는 0 ~ 10 사이어야 합니다. - " + leftPinCount);
    }

    @DisplayName("현재 투구에 맞는 상태를 가져온다.")
    @ParameterizedTest
    @CsvSource({"10, 0, STRIKE", "1, 0, SPARE", "8, 2, MISS", "0, 3, GUTTER"})
    void getPitchState(int pinCount, int leftPinCount, State expectedState) {
        Pitch pitch = new Pitch(pinCount, leftPinCount);

        assertThat(pitch.getState()).isEqualTo(expectedState);
    }
}