package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("계산 불가능한 스코어 테스트")
class InCalculableScoreTest {

    @DisplayName("계산 불가능한 스코어는 점수 없이 초기화 가능하다")
    @Test
    void init() {
        assertThat(InCalculableScore.init()).isInstanceOf(InCalculableScore.class);
    }

    @DisplayName("계산 불가능한 스코어는 계산이 불가능하다")
    @Test
    void isCalculable() {
        assertThat(InCalculableScore.init().isCalculable()).isFalse();
    }

}
