package bowling.domain.state;

import bowling.domain.score.CalculableScore;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.domain.Fixture.DOWNED_PINS_2;
import static bowling.domain.Fixture.DOWNED_PINS_5;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("두 번 투구하여 핀이 남은 경우의 상태 테스트")
class MissTest {

    @DisplayName("Miss 상태는 두 개의 쓰러진 핀 객체를 가지고 초기화한다")
    @Test
    void init() {
        assertThat(Miss.of(DOWNED_PINS_5, DOWNED_PINS_2)).isInstanceOf(Miss.class);
    }

    @DisplayName("Miss 상태에서 isMiss 테스트")
    @Test
    void isMiss() {
        Miss miss = Miss.of(DOWNED_PINS_5, DOWNED_PINS_2);

        assertThat(miss.isMiss()).isTrue();
    }

    @DisplayName("Miss 상태에서의 스코어는 계산 가능하며 그 값은 두개의 핀의 합과 같다")
    @Test
    void score() {
        Miss miss = Miss.of(DOWNED_PINS_5, DOWNED_PINS_2);
        Score score = miss.Score();

        assertThat(score).isInstanceOf(CalculableScore.class);
        assertThat(score.toInt()).isEqualTo(7);
    }
}
