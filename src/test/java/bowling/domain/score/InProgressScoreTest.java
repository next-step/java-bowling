package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @DisplayName("계산중인 스코어에 남은 카운트는 3 이상이 될 수 없다.") // 스트라이크라고 하더라도 추가적으로 필요한 점수는 2회분
    @Test
    void initException() {
        assertThatThrownBy(() -> InProgressScore.init(10, 3)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("스코어 끼리 더하면 새로운 스코어를 반환한다")
    @Test
    void add() {
        InProgressScore inProgressScore = InProgressScore.init(10, 2);
        Score score = Score.from(10);

        assertThat(inProgressScore.add(score)).isInstanceOf(Score.class);
    }

    @DisplayName("계산중인 스코어 객체는 스코어 값이 같아면 동일하고, 남은 횟수가 같으면 같은 객체로 판별한다.")
    @Test
    void equals() {
        InProgressScore inProgressScore = InProgressScore.init(10, 2);
        InProgressScore anotherInProgressScore = InProgressScore.init(10, 2);
        InProgressScore theOtherInProgressScore = InProgressScore.init(10, 1);

        assertThat(inProgressScore).isEqualTo(anotherInProgressScore);
        assertThat(inProgressScore).isNotEqualTo(theOtherInProgressScore);
    }

    @DisplayName("스트라이크의 스코어는 점수가 10에 남은 횟수가 2이다")
    @Test
    void ofStrike() {
        assertThat(InProgressScore.ofStrike()).isEqualTo(InProgressScore.init(10, 2));
    }

    @DisplayName("스페어의 스코어는 점수가 10에 남은 횟수가 1이다")
    @Test
    void ofSpare() {
        assertThat(InProgressScore.ofSpare()).isEqualTo(InProgressScore.init(10, 1));
    }
}
