package bowling.score;

import bowling.global.exception.InputScoreNullPointerException;
import bowling.global.exception.OutOfScoreRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ScoreTest {

    private Score score;

    @Test
    @DisplayName("입력한 값 검증")
    void score() {
        score = Score.from("5");
        assertThat(score.getScore()).isEqualTo(5);
    }

    @Test
    @DisplayName("입력한 값의 범위 검증. 0 ~ 10의 값만 허용한다.")
    void pitch() {
        score = Score.from("5");
        assertThat(score.getScore()).isBetween(0, 10);
    }

    @Test
    @DisplayName("입력한 값이 Strike이면 True")
    void strike() {
        score = Score.from("10");
        assertThat(score.isStrike()).isTrue();
    }

    @Test
    @DisplayName("입력한 값이 Gutter이면 True")
    void gutter() {
        score = Score.from("0");
        assertThat(score.isGutter()).isTrue();
    }

    @Test
    @DisplayName("입력값의 범위가 0 ~ 10을 벗어나는 경우 Exception 발생")
    void validateScoreRange() {
        assertThatExceptionOfType(OutOfScoreRangeException.class)
                .isThrownBy(() -> {
                    Score.from("11");
                });
    }

    @ParameterizedTest
    @DisplayName("입력값이 null일 경우 Exception 발생")
    @NullAndEmptySource
    void validateScoreIsNull(String input) {
        assertThatExceptionOfType(InputScoreNullPointerException.class)
                .isThrownBy(() -> {
                    Score.from(input);
                });
    }

}
