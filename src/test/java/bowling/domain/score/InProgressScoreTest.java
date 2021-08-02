package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("계산중인 스코어 테스트")
class InProgressScoreTest {

    @DisplayName("계산중인 스코어는 계산 완료까지 필요한 남은 카운트와, 현재의 점수를 가지고 초기화한다")
    @Test
    void init() {
        assertThat(InProgressScore.init(10, 2)).isInstanceOf(InProgressScore.class);
    }

    @DisplayName("계산중인 스코어는 계산이 불가능 하다")
    @Test
    void isCalculable() {
        InProgressScore inProgressScore = InProgressScore.init(10, 2);
        assertThat(inProgressScore.isCalculable()).isFalse();
    }
}
