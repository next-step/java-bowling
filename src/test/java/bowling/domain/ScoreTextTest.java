package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("ScoreText 테스트")
class ScoreTextTest {

    @DisplayName("점수를 기호로 변경되는지 확인")
    @Test
    void getScoreTextByScore() {
        for (int i = 0; i < 10; i++) {
            assertThat(ScoreText.getScoreTextByScore(i)).isEqualTo(ScoreText.values()[i].getScoreText());
        }
    }

    @DisplayName("점수를 벗어났을때 예외처리")
    @ParameterizedTest
    @ValueSource(ints = {-1,11})
    void ScoreTextException(int score) {
        assertThatThrownBy(() -> ScoreText.getScoreTextByScore(score))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("허용값을 벗어난 스코어입니다.");
    }
}
