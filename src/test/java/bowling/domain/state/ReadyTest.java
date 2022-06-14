package bowling.domain.state;

import bowling.domain.Score;
import bowling.exception.CannotCalculateScore;
import bowling.exception.NotSupportMethodException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReadyTest {

    @Test
    @DisplayName("10개를 히트하면 스트라이크 상태를 반환한다.")
    void hit_10() {
        assertThat(new Ready().bowl(10)).isInstanceOf(Strike.class);
    }

    @ParameterizedTest(name = "{0}개를 히트하면 FirstHit 상태를 반환한다.")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    void hit_less_10(int hit) {
        assertThat(new Ready().bowl(hit)).isEqualTo(new FirstHit(hit));
    }

    @Test
    @DisplayName("Ready 상태는 활성화 상태이다.")
    void name() {
        assertThat(new Ready().isFinish()).isFalse();
    }

    @Test
    @DisplayName("Ready 의 description 은 공백이다")
    void description() {
        assertThat(new Ready().description()).isEqualTo("");
    }

    @Test
    @DisplayName("Ready 의 경우 보너스 찬스가 없다")
    void hasBonusChance() {
        assertThat(new Ready().hasBonusChance()).isFalse();
    }

    @Test
    @DisplayName("Ready 의 bowlingCount 는 0이다.")
    void bowlingCount() {
        assertThat(new Ready().bowlingCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("Ready 의 score 는 프레임이 종료되지 않아 예외를 반환한다.")
    void score() {
        assertThatThrownBy(() -> new Ready().score()).isInstanceOf(NotSupportMethodException.class);
    }

    @Test
    @DisplayName("Ready 의 경우 추가계산 호출 시 예외를 반환한다.")
    void calculateAdditionalScore_1() {
        Score score = Score.of(10, 1);

        assertThatThrownBy(() -> new Ready().calculateAdditionalScore(score)).isInstanceOf(CannotCalculateScore.class);
    }

    @Test
    @DisplayName("Ready 의 경우 진행 중 상태이 아니다.")
    void isProgressing() {
        assertThat(new Ready().isProgressing()).isFalse();
    }
}