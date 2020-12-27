package step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PitchTest {

    @Test
    @DisplayName("투구 점수 입력")
    void createPitch() {
        assertThat(Pitch.from(10).toString()).isEqualTo("10");
    }

    @Test
    @DisplayName("점수가 10점을 초과할 경우 예외 처리")
    void exceptOverTen() {
        assertThrows(IllegalArgumentException.class,
                () -> Pitch.from(11));
    }

    @Test
    @DisplayName("점수가 10점을 초과할 경우 예외 처리")
    void exceptBelowZero() {
        assertThrows(IllegalArgumentException.class,
                () -> Pitch.from(-1));
    }

}