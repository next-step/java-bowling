package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("계산 가능한 스코어 테스트")
class CalculableScoreTest {

    @DisplayName("계산 가능한 스코어는 점수만 가지고 초기화 한다")
    @Test
    void init() {
        assertThat(CalculableScore.from(30)).isInstanceOf(CalculableScore.class);
    }

    @DisplayName("계산 가능한 스코어는 더 이상 스코어를 더할 수 없다")
    @Test
    void addException() {
        CalculableScore calculableScore = CalculableScore.from(25);
        InProgressScore anotherScore = InProgressScore.init(10, 2);
        assertThatThrownBy(() -> calculableScore.add(anotherScore)).isInstanceOf(IllegalStateException.class);
    }

}
