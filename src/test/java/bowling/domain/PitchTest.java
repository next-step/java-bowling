package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PitchTest {

    @DisplayName("Pitch 값 생성 테스트")
    @Test
    void pitch_투구_값_생성테스트() {
        // given
        Pitch pitch = Pitch.valueOf(10);
        // when
        Pitch expected = Pitch.valueOf(10);
        // then
        assertThat(pitch).isEqualTo(expected);
    }

    @DisplayName("Pitch 값 생성 예외 테스트")
    @ParameterizedTest(name = "0 ~ 10 범위 외의 값: `{0}` 입력 시 볼링 예외처리")
    @ValueSource(ints = {-1, 11})
    void pitch_투구_값_예외테스트(int given) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Pitch.valueOf(given));
    }
}
