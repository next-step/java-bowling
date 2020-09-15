package bowling.pitching;

import bowling.global.exception.InputPitchPointNullPointerException;
import bowling.global.exception.OutOfPitchRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PitchingTest {

    private Pitching pitching;

    @Test
    @DisplayName("투구 포인트 테스트")
    void pitchPoint() {
        pitching = Pitching.pitch("5", 1);
        assertThat(pitching.getPitchingPoint()).isEqualTo(5);
    }

    @Test
    @DisplayName("투구 횟수 테스트")
    void countCount() {
        pitching = Pitching.pitch("10", 2);
        assertThat(pitching.getPitchingCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("투구 범위 테스트. 0 ~ 10의 값만 허용한다.")
    void pitch() {
        pitching = Pitching.pitch("5", 1);
        assertThat(pitching.getPitchingPoint()).isBetween(0, 10);
    }

    @Test
    @DisplayName("투구 입력값의 범위가 0 ~ 10을 벗어나는 경우 Exception 발생")
    void validatePitchRange() {
        assertThatExceptionOfType(OutOfPitchRangeException.class)
                .isThrownBy(() -> {
                    Pitching.pitch("11", 1);
                });
    }

    @ParameterizedTest
    @DisplayName("투구 입력값이 null일 경우 Exception 발생")
    @NullAndEmptySource
    void validateInputPitchIsNull(String input) {
        assertThatExceptionOfType(InputPitchPointNullPointerException.class)
                .isThrownBy(() -> {
                    Pitching.pitch(input, 1);
                });
    }

}
