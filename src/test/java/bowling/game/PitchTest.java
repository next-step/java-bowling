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
        assertThatThrownBy(() -> Pitch.firstPitch(pinCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("쓰러진 핀의 갯수는 0 ~ 10 사이어야 합니다. - " + pinCount);
    }

    @DisplayName("남은핀 보다 쓰러트릴 핀이 크면 아니면 IllegalArgumentsException throw")
    @ParameterizedTest
    @CsvSource({"7, 4", "3, 9"})
    void validateLeftPinCount(int firstPinCount, int nextPinCount) {
        Pitch pitch = Pitch.firstPitch(firstPinCount);
        assertThatThrownBy(() -> pitch.nextPitch(nextPinCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("쓰러트릴 핀의 갯수보다 남은 핀의 갯수가 적습니다. 남게될 핀 - " + (10 - firstPinCount - nextPinCount));
    }

    @DisplayName("현재 투구에 맞는 상태를 가져온다.")
    @ParameterizedTest
    @CsvSource({"10, 0, STRIKE", "1, 0, SPARE", "8, 2, MISS", "0, 3, GUTTER"})
    void getPitchState(int pinCount, int leftPinCount, State expectedState) {
        Pitch pitch = new Pitch(pinCount, leftPinCount);

        assertThat(pitch.getState()).isEqualTo(expectedState);
    }
}