package step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PitchTest {

    @ParameterizedTest
    @CsvSource(
            value = {"1:1", "2:2", "3:3", "4:4", "5:5", "6:6", "7:7", "8:8", "9:9"},
            delimiter = ':')
    @DisplayName("투구 점수 입력")
    void createPitch(int score, String value) {
        assertThat(Pitch.from(score).toString()).isEqualTo(value);
    }

    @Test
    @DisplayName("0점 입력")
    void zeroPitch() {
        assertThat(Pitch.from(0).toString()).isEqualTo("-");
    }

    @Test
    @DisplayName("10점 입력")
    void tenPitch() {
        assertThat(Pitch.from(10).toString()).isEqualTo("X");
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