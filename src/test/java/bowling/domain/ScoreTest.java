package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {

    @DisplayName("점수 생성 테스트")
    @Test
    void createScoreTest() {
        new Score(5, 0);
    }

    @DisplayName("score는 0이상이어야 하고, 남은 횟수는 0~2어야 한다. 범위를 벗어날 경우 예외가 발생한다.")
    @ParameterizedTest
    @CsvSource(value = {"-1:1", "1:3", "1:-1", "-1:-1"}, delimiter = ':')
    void createScoreTestFail(int score, int leftCount) {
        assertThatThrownBy(() -> new Score(score, leftCount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("어떤 Score일 때 공을 굴리면 점수는 증가하고, 남은 횟수는 감소한다.")
    @Test
    void bowlTest() {
        Score score = new Score(5, 1).bowl(new Pins(3));
        assertThat(score).isEqualTo(new Score(8, 0));
    }

    @DisplayName("남은 횟수가 0일 때 공을 굴리면 예외가 발생한다.")
    @Test
    void bowlTestFail() {
        assertThatThrownBy(() -> new Score(5, 0).bowl(new Pins(3)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("점수를 반환한다.")
    @Test
    void scoreTest() {
        assertThat(new Score(5, 1).score()).isEqualTo(5);
    }

    @DisplayName("남은 횟수가 0이면 점수를 계산할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"0:true", "1:false", "2:false"}, delimiter = ':')
    void canCalculateTest(int count, boolean canCalculate) {
        assertThat(new Score(5, count).canCalculate())
                .isEqualTo(canCalculate);
    }
}