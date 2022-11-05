package bowling.domain;

import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("스코어 테스트")
class ScoreTest {

    @DisplayName("스트라이크인 경우 2번 점수를 더해야 점수 계산이 가능하다.")
    @Test
    void strike() {
        Strike strike = new Strike();
        Score score = strike.getScore();

        Score result = score.add(1)
                .add(2);

        assertThat(result.canCalculateScore()).isTrue();
    }

    @DisplayName("스패어인 경우 1번 점수를 더해야 점수 계산이 가능하다.")
    @Test
    void spare() {
        Spare spare = new Spare(Pin.of(5), Pin.of(5));
        Score score = spare.getScore();

        Score result = score.add(1);

        assertThat(result.canCalculateScore()).isTrue();
    }

    @DisplayName("MISS 인 경우 바로 점수 계산이 가능하다.")
    @Test
    void miss() {
        Miss miss = new Miss(Pin.of(1), Pin.of(2));
        Score score = miss.getScore();

        assertThat(score.canCalculateScore()).isTrue();
    }
}
