package bowling.domain.state;

import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {

    @DisplayName("strike로 입력받은 점수를 계산하면 해당 점수의 count를 감소시키고, true를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void canCalculateTestTrue(int count) {
        Strike strike = new Strike();
        assertThat(strike.canCalculate(new Score(5, count))).isTrue();
    }

    @DisplayName("strike와 점수를 더하면 합한 점수를 반환한다.")
    @Test
    void sumBeforeScoreTest() {
        Strike strike = new Strike();
        assertThat(strike.sumBeforeScore(new Score(5, 1)))
                .isEqualTo(new Score(15, 0));
    }

    @DisplayName("strike의 점수를 반환한다.")
    @Test
    void scoreTest() {
        assertThat(new Strike().score()).isEqualTo(new Score(10, 2));
    }
}