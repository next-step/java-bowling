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
        assertThat(new SomeScore(10)).isInstanceOf(Score.class);
    }

    @DisplayName("점수 객체는 본인이 계산 가능한 상태인지 확인할 수 있어야 한다")
    @Test
    void isCalculable() {
        assertThat((new SomeScore(10)).isCalculable()).isFalse();
    }

    @DisplayName("볼링 점수를 초기화 할때 점수의 최대값은 30을 넘길 수 없다")
    @Test
    void initException() {
        assertThatThrownBy(() -> new SomeScore(31)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("점수끼리는 더할 수 있으며, 더한 결과는 새로운 스코어를 반환한다")
    @Test
    void add() {
        SomeScore someScore = new SomeScore(10);
        SomeScore anotherSomeScore = new SomeScore(10);

        assertThat(someScore.add(anotherSomeScore)).isInstanceOf(Score.class);
    }

    static class SomeScore extends Score {
        protected SomeScore(int score) {
            super(score);
        }

        @Override
        public boolean isCalculable() {
            return false;
        }

        @Override
        public Score add(Score anotherScore) {
            return new SomeScore(score + anotherScore.score);
        }
    }
}
