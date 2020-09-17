package bowling.score;

import bowling.global.exception.ScoreMaxSizeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ScoresTest {

    private Scores scores;

    @BeforeEach
    void setUp() {
        scores = Scores.newInstance();
    }

    @Test
    @DisplayName("2개의 입력값이 10일 경우 Spare")
    void isSpare() {
        scores.add(Score.from("7"));
        scores.add(Score.from("3"));
        assertThat(scores.isSpare()).isTrue();
    }

    @Test
    @DisplayName("남은 Pin 검증")
    void getRemainingPins() {
        scores.add(Score.from("7"));
        assertThat(scores.getRemainingPins()).isEqualTo(3);
    }

    @Test
    @DisplayName("Scores의 최대 사이즈가 3을 넘을 경우 Exception 발생")
    void scoresMaxSizeException() {
        assertThatExceptionOfType(ScoreMaxSizeException.class)
                .isThrownBy(() -> {
                    scores.add(Score.from("2"));
                    scores.add(Score.from("3"));
                    scores.add(Score.from("3"));
                    scores.add(Score.from("3"));
                });
    }

    @Test
    @DisplayName("Scores의 합이 10이 넘을 경우 Exception 발생")
    void sumAllMaxIsTenException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    scores.add(Score.from("7"));
                    scores.add(Score.from("4"));
                    scores.getScores();
                });
    }
}
