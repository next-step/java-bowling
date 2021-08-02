package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("계산 가능한 스코어 테스트")
class CalculableScoreTest {

    @DisplayName("계산 가능한 스코어는 점수만 가지고 초기화 한다")
    @Test
    void init() {
        assertThat(CalculableScore.from(30)).isInstanceOf(CalculableScore.class);
    }

}
