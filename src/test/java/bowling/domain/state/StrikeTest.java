package bowling.domain.state;

import bowling.domain.Score;
import bowling.exception.NotSupportMethodException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StrikeTest {

    @Test
    @DisplayName("스트라이크 상태에서는 투구 시 예외를 반환한다.")
    void bowl() {
        assertThatThrownBy(() -> new Strike().bowl(3)).isInstanceOf(NotSupportMethodException.class);
    }

    @Test
    @DisplayName("스트라이크 상태에는 종료상태이다.")
    void isFinish() {
        assertThat(new Strike().isFinish()).isTrue();
    }

    @Test
    @DisplayName("Strike 의 description 은 X 이다")
    void description() {
        assertThat(new Strike().description()).isEqualTo("X");
    }

    @Test
    @DisplayName("Strike 의 경우 보너스 찬스가 있다")
    void hasBonusChance() {
        assertThat(new Strike().hasBonusChance()).isTrue();
    }

    @Test
    @DisplayName("Strike 의 bowlingCount 는 1이다.")
    void bowlingCount() {
        assertThat(new Strike().bowlingCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("Strike 의 점수는 Strike Score 를 반환한다.")
    void score() {
        assertThat(new Strike().score()).isEqualTo(Score.strike());
    }

    @Test
    @DisplayName("Strike 의 경우 추가계산이 필요한 점수에 10점을 추가한다.")
    void calculateAdditionalScore() {
        Score score = Score.of(10, 1);

        Score calculatedScore = new Strike().calculateAdditionalScore(score);

        assertThat(calculatedScore).isEqualTo(Score.of(20, 0));
    }

    @Test
    @DisplayName("Strike 의 경우 진행 중 상태이 아니다.")
    void isProgressing() {
        assertThat(new Strike().isProgressing()).isFalse();
    }
}