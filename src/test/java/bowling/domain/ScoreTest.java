package bowling.domain;

import bowling.exception.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ScoreTest {
    @DisplayName("더할 투구 (left) 가 0이 아니면 점수를 계산할 수 없다.")
    @ParameterizedTest
    @CsvSource(value = {"10:1", "1:1", "10:2", "2:2"}, delimiter = ':')
    void canCalculate_false(int score, int left) {
        assertThat(new Score(score, left).canCalculateScore()).isFalse();
    }

    @DisplayName("left 가 0 이 아니면 점수를 계산할 수 없다.")
    @ParameterizedTest
    @CsvSource(value = {"1:2:3", "2:5:7", "4:1:5"}, delimiter = ':')
    void getScore_error(int score, int left) {
        assertThatThrownBy(() -> new Score(score, left).getScore())
                .isInstanceOf(BusinessException.class);
    }

    @DisplayName("노멀 투구(2회)를 던졌을 때 점수를 계산할 수 있고 누적합이다.")
    @ParameterizedTest
    @CsvSource(value = {"1:2:3", "2:5:7"}, delimiter = ':')
    void normal_score(int first, int second, int result) {
        assertThat(new Score(first, 1).pitch(second).getScore()).isEqualTo(result);
    }

    @DisplayName("스트라이크 스코어를 생성하는데 left 수가 동일 하다.")
    @Test
    void strikeScore() {
        assertThat(Score.ofStrike()).isEqualTo(new Score(10, 2));
    }

    @DisplayName("스페어 스코어를 생성하는데 left 수가 동일 하다..")
    @Test
    void spareScore() {
        assertThat(Score.ofSpare()).isEqualTo(new Score(10, 1));
    }
}