package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("계산 불가능한 스코어 테스트")
class IncalculableScoreTest {

    @DisplayName("계산 불가능한 스코어는 계산까지 필요한 덧셈 카운트가 존재한다")
    void init() {
        assertThat(new InProgressScore(10, 2)).isInstanceOf(InProgressScore.class);
    }

}
