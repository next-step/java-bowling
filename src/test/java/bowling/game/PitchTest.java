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

    @DisplayName("현재 투구에 맞는 상태를 가져온다.")
    @ParameterizedTest
    @CsvSource({"10, STRIKE", "8, MISS", "0, GUTTER"})
    void getPitchState(int pinCount, State expectedState) {
        Pitch pitch = Pitch.firstPitch(pinCount);

        assertThat(pitch.getState()).isEqualTo(expectedState);
    }

    @DisplayName("두번째 투구의 상태를 가져온다.")
    @ParameterizedTest
    @CsvSource({"8, 2, SPARE", "7, 2, MISS", "8, 0, GUTTER"})
    void getNextPitchState(int firstPinCount, int nextPinCount, State expectedState) {
        Pitch firstPitch = Pitch.firstPitch(firstPinCount);
        Pitch nextPitch = firstPitch.nextPitch(nextPinCount);

        assertThat(nextPitch.getState()).isEqualTo(expectedState);
    }
}