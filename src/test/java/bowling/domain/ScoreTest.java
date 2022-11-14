package bowling.domain;

import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Score 테스트")
class ScoreTest {

    @DisplayName("Strike 인 경우 2번 점수를 더해야 점수 계산이 가능하다.")
    @Test
    void strike() {

        final Score strike = new Strike().getScore();
        final Score result = strike.add(1).add(2);

        assertThat(result.canCalculateScore()).isTrue();
    }

    @DisplayName("Spare 인 경우 1번 점수를 더해야 점수 계산이 가능하다.")
    @ParameterizedTest(name = "첫번째 넘어진 개수는 {0}이고, 두번째 넘어진 개수는 {1}이다.")
    @CsvSource(value = {"0,10", "5,5", "2,8"})
    void pin_spare(final int one, final int two) {

        final Spare spare = new Spare(new Pin(one), new Pin(two));
        final Score score = spare.getScore();
        final Score result = score.add(1);

        assertThat(result.canCalculateScore()).isTrue();
    }

    @DisplayName("Miss 인 경우 바로 점수 계산한다..")
    @Test
    void miss() {

        final Miss miss = new Miss(new Pin(1), new Pin(2));
        Score score = miss.getScore();

        assertThat(score.canCalculateScore()).isTrue();
    }
}