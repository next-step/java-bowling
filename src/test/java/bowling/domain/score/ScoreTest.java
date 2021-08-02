package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("점수를 계산하기 위한 Score 클래스 테스트")
class ScoreTest {

    // 점수는 상태값에 따라 계산하는 방식이 달라진다.
    // 상태에 따라선 계산이 불가능 할 수 있다.
    // 따라서 계산 불가 Score 객체가 있으면 좋을듯
    @DisplayName("점수 객체는 점수를 가지고 초기화 한다")
    @Test
    void init() {
        assertThat(Score.from(10)).isInstanceOf(Score.class);
    }

    @DisplayName("점수 객체는 본인이 계산 가능한 상태인지 확인할 수 있어야 한다")
    @Test
    void isCalculable() {
        assertThat(Score.from(10).isCalculable()).isFalse();
    }

    @DisplayName("볼링 점수를 초기화 할때 점수의 최대값은 30을 넘길 수 없다")
    @Test
    void initException() {
        assertThatThrownBy(() -> Score.from(31)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("점수끼리는 더할 수 있으며, 더한 결과는 새로운 스코어를 반환한다")
    @Test
    void add() {
        Score score = Score.from(10);
        Score anotherScore = Score.from(10);

        assertThat(score.add(anotherScore)).isInstanceOf(Score.class);
    }

    @DisplayName("스코어 객체는 스코어 값이 같아면 동일한 객체로 판별한다.")
    @Test
    void equals() {
        assertThat(Score.from(10)).isEqualTo(Score.from(10));
    }
}
