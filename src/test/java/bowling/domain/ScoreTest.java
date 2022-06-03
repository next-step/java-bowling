package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {
    @DisplayName("Score 객체를 생성한다.")
    @Test
    void score_생성() {
        assertThat(new Score(10, 2)).isNotNull().isInstanceOf(Score.class);
    }

    @DisplayName("점수가 0 ~ 30을 벗어날 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-2, -1, 31, 32})
    void score_생성_점수_예외(int score) {
        assertThatThrownBy(() -> new Score(score, 0)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 기회가 0 ~ 2을 벗어날 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-2, -1, 3, 4})
    void score_생성_보너스기회_예외(int bonusChanceCount) {
        assertThatThrownBy(() -> new Score(10, bonusChanceCount)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 기회가 n 일때, 점수가 30 - 10 * n 보다 클 경우 예외가 발생한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "0, 31",
            "1, 21",
            "2, 11"
    })
    void score_생성_보너스_점수_예외(int bonusChanceCount, int score) {
        assertThatThrownBy(() -> new Score(score, bonusChanceCount)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("스트라이크 점수")
    @Test
    void strike_점수() {
        assertThat(Score.strike()).isEqualTo(new Score(10, 2));
    }

    @DisplayName("스페어 점수")
    @Test
    void spare_점수() {
        assertThat(Score.spare()).isEqualTo(new Score(10, 1));
    }

    @DisplayName("미스 점수")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 8, 9})
    void miss_점수(int totalHitPins) {
        assertThat(Score.miss(totalHitPins)).isEqualTo(new Score(totalHitPins, 0));
    }

    @DisplayName("미스 점수가 10 이상일 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {10, 11, 12})
    void miss_점수_예외(int totalHitPins) {
        assertThatThrownBy(() -> Score.miss(totalHitPins)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("점수 값을 더한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "1, 11",
            "2, 12",
            "5, 15",
            "9, 19"
    })
    void addScore(int addScore, int resultScore) {
        Score previousScore = new Score(10, 2);
        assertThat(previousScore.addScore(addScore)).isEqualTo(new Score(resultScore, 1));
    }

    @DisplayName("보너스 기회가 남아있는지 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "0, true",
            "1, false",
            "2, false"
    })
    void isNoBonusChance_true(int bonusChanceCount, boolean trueOrFalse) {
        assertThat(new Score(10, bonusChanceCount).isNoBonusChance()).isEqualTo(trueOrFalse);
    }

    @DisplayName("사용 불가능한 점수인지 확인한다.")
    @Test
    void isNotAvailableScore() {
        assertThat(Score.init().isNotAvailableScore()).isTrue();
        assertThat(new Score(10, 2).isNotAvailableScore()).isFalse();
    }

    @DisplayName("계산 불가능한 점수인지 확인한다.")
    @Test
    void isNotCalculable() {
        assertThat(new Score(10, 0).isNotCalculable()).isTrue();
        assertThat(Score.init().isNotCalculable()).isTrue();
    }
}