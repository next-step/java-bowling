package bowling.score;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ScoresTest {

    private Scores scores;

    @BeforeEach
    void setUp() {
        scores = NormalScores.newInstance();
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
    @DisplayName("첫번째의 점수보다 큰 값을 두번째 점수때 입력할 경우 Exception 발생")
    void scoreLaretIsRemainingPinsException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    scores.add(Score.from("7"));
                    scores.add(Score.from("4"));
                });
    }
}
