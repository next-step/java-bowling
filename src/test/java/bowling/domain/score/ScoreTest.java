package bowling.domain.score;

import bowling.exception.BonusCountNullPointerException;
import bowling.exception.InvalidScoreSizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {

    @DisplayName("Score 인스턴스 셍성 여부 테스트")
    @Test
    void 생성() {
        // given
        int scorePoint = 10;
        BonusCount bonusCount = BonusCount.strike();

        // when
        Score score = Score.of(scorePoint, bonusCount);

        // then
        assertThat(score).isNotNull();
    }

    @DisplayName("Score 인스턴스에 들어오는 BonusCount 가 null 인지 테스트")
    @Test
    void 검증_null() {
        // given
        int scorePoint = 10;
        BonusCount bonusCount = null;

        // when and then
        assertThatThrownBy(() -> Score.of(scorePoint, bonusCount))
                .isInstanceOf(BonusCountNullPointerException.class)
                .hasMessage("BonusCount 인스턴스가 null 입니다.");

    }

    @DisplayName("Score 인스턴스에 들어오는 값이 범위르 벗어난 경우 예외처리 테스트")
    @Test
    void 검증_범위를_벗어난_값() {
        // given
        int negativeScorePoint = -2;
        int overSizeScorePoint = 31;
        BonusCount bonusCount = BonusCount.strike();

        // when and then
        assertThatThrownBy(() -> Score.of(negativeScorePoint, bonusCount))
                .isInstanceOf(InvalidScoreSizeException.class)
                .hasMessage("Score 범위를 벗어난 값이 입력 되었습니다.");

        // when and then
        assertThatThrownBy(() -> Score.of(overSizeScorePoint, bonusCount))
                .isInstanceOf(InvalidScoreSizeException.class)
                .hasMessage("Score 범위를 벗어난 값이 입력 되었습니다.");
    }
}