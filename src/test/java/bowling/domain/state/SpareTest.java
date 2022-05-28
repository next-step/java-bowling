package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SpareTest {

    @DisplayName("Spare를 생성한다.")
    @Test
    void createSpareTest() {
        new Spare(new Pins(5), new Pins(5));
        new Spare(new Pins(8), new Pins(2));
    }

    @DisplayName("첫 번째 시도, 두 번째 시도의 Pin의 개수 합이 10개가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @CsvSource(value = {"9:0", "9:4"}, delimiter = ':')
    void createSpareTestFail(int first, int second) {
        assertThatThrownBy(() -> new Spare(new Pins(first), new Pins(second)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이전 점수의 count를 감소시키고 계산할 수 있는지 확인한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void canCalculateTest(int count) {
        Score score = new Score(5, count);
        assertThat(new Spare(new Pins(5), new Pins(5)).canCalculate(score))
                .isTrue();
    }

    @DisplayName("이전 점수와 더한 점수를 반환한다.")
    @Test
    void sumBeforeScoreTest() {
        Spare spare = new Spare(new Pins(5), new Pins(5));
        Strike strike = new Strike();
        assertThat(spare.sumBeforeScore(strike.score()))
                .isEqualTo(new Score(20, 0));
    }

    @DisplayName("Spare의 점수를 반환한다.")
    @Test
    void scoreTest() {
        assertThat(new Spare(new Pins(5), new Pins(5)).score())
                .isEqualTo(new Score(10, 1));
    }
}