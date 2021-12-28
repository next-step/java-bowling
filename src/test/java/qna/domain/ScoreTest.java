package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class ScoreTest {

    @DisplayName("Score 생성")
    @Test
    void create() {
        // when & then
        assertAll(
                () -> assertThat(Score.of(1, 0)).isNotNull(),
                () -> assertThat(Score.withNonRemainingPitches(8)).isNotNull(),
                () -> assertThat(Score.withReady()).isNotNull()
        );
    }

    @DisplayName("Ready Score 확인")
    @Test
    void createReadyScore() {
        // given
        Score readyScore = Score.withReady();
        // when & then
        assertAll(
                () -> assertThat(readyScore.getValue()).isEqualTo(Score.INCOMPUTABLE_SCORE_VALUE),
                () -> assertThat(readyScore.hasFinalScore()).isFalse(),
                () -> assertThatExceptionOfType(IllegalStateException.class)
                        .isThrownBy(readyScore::getFinalValue)
        );
    }

    @DisplayName("잘못된 점수로 Score 생성 예외")
    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, Score.MIN_SCORE - 1, Score.MAX_SCORE + 1, Integer.MAX_VALUE})
    void createFailedWithInvalidValue(int invalidValue) {
        // given
        int validExtraPitches = 2;
        // when & then
        assertThatIllegalArgumentException().isThrownBy(
                () -> Score.of(invalidValue, validExtraPitches)
        );
    }

    @DisplayName("잘못된 추가 투구로 Score 생성 예외")
    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1, 3, Integer.MAX_VALUE})
    void createFailedWithInvalidExtraPitches(int invalidExtraPitches) {
        // given
        int validValue = 5;
        // when & then
        assertThatIllegalArgumentException().isThrownBy(
                () -> Score.of(validValue, invalidExtraPitches)
        );
    }

    @DisplayName("추가 투구가 없는 투구 이후 점수 확인")
    @Test
    void bowlWithNonLeftScore() {
        // given
        Score score = Score.withNonRemainingPitches(5);
        // when & then
        assertAll(
                () -> assertThat(score).isEqualTo(Score.of(5, 0)),
                () -> assertThat(score.getFinalValue()).isEqualTo(5)
        );
    }

    @DisplayName("Spare 이후, 추가 1회 투구 점수 합산")
    @Test
    void bowlWithSpareNextScore() {
        // given
        Score spareScore = Score.of(10, 1);
        // when
        Score finalScore = spareScore.bowl(Score.withNonRemainingPitches(5));
        // then
        assertAll(
                () -> assertThat(finalScore).isEqualTo(Score.of(10 + 5, 0)),
                () -> assertThat(finalScore.getFinalValue()).isEqualTo(15)
        );
    }

    @DisplayName("Strike 이후, 추가 2회 투구 점수 합산")
    @Test
    void bowlWithStrikeNextScore() {
        // given
        Score strikeScore = Score.of(10, 2);
        // when
        Score middleScore = strikeScore.bowl(Score.withNonRemainingPitches(5));
        // then
        assertThat(middleScore).isEqualTo(Score.of(10 + 5, 1));
        // when
        Score finalScore = middleScore.bowl(Score.withNonRemainingPitches(3));
        // then
        assertAll(
                () -> assertThat(finalScore).isEqualTo(Score.of(10 + 5 + 3, 0)),
                () -> assertThat(finalScore.getFinalValue()).isEqualTo(18)
        );
    }

    @DisplayName("투구가 남아있는 경우, 최종 점수 반환 예외 발생과 정상 반환 확인")
    @Test
    void getFinalValue() {
        // given
        int spareValue = 10;
        Score spareScore = Score.of(spareValue, 1);
        // when & then
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(spareScore::getFinalValue);
        // given
        int basicScore = 3;
        // when
        Score finalScore = spareScore.bowl(Score.withNonRemainingPitches(basicScore));
        // then
        assertThat(finalScore.getFinalValue()).isEqualTo(spareValue + basicScore);
    }

    @DisplayName("투구가 남아있는 경우, 점수 반환 확인")
    @Test
    void getValue() {
        // given
        int spareValue = 10;
        Score spareScore = Score.of(10, 1);
        // when & then
        assertThat(spareScore.getValue()).isEqualTo(spareValue);
        // given
        int basicScore = 3;
        // when
        Score finalScore = spareScore.bowl(Score.withNonRemainingPitches(basicScore));
        // then
        assertThat(finalScore.getValue()).isEqualTo(spareValue + basicScore);
    }
}
