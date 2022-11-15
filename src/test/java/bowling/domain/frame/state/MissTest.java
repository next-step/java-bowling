package bowling.domain.frame.state;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.frame.Score;

class MissTest {
    @DisplayName("조건을 만족하지 않는 핀의 갯수를 넣어줄 때 예외를 던진다.")
    @ParameterizedTest(name = "{displayName} 핀: {0}, {1}")
    @CsvSource({
        "10,0",
        "5,5"
    })
    void createWithInvalidPins(int firstPins, int secondPins) {
        assertThatThrownBy(() -> new Miss(new Pins(firstPins), new Pins(secondPins)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Miss의 조건을 만족하지 않습니다");
    }

    @Test
    @DisplayName("Miss 상태에서는 볼링을 시도할 시 예외를 던진다.")
    void bowl() {
        assertThatThrownBy(() -> new Miss(new Pins(4), new Pins(5)).bowl(10))
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("Miss 상태에서는 해당 프레임의 종료를 알린다.")
    void isFinish() {
        assertThat(new Miss(new Pins(4), new Pins(5)).isFinish()).isTrue();
    }

    @Test
    @DisplayName("Score 객체를 요구할 시에, 계산 완료된 Score 객체를 반환한다.")
    void getScore() {
        Miss miss = new Miss(new Pins(4), new Pins(5));
        Score score = miss.getScore();
        assertThat(score).isEqualTo(new Score(9, 0));
        assertThat(score.canCalculateScore()).isTrue();
    }

    @ParameterizedTest(name = "주어진 스코어에 따라 보너스 점수를 계산해주어야 한다; {1}")
    @MethodSource("provideScoreSource")
    void calculateBonusScore(Miss miss, Score score, Score expected) {
        assertThat(miss.calculateBonusScore(score)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideScoreSource() {
        Miss miss = new Miss(new Pins(4), new Pins(5));
        return Stream.of(
            Arguments.of(miss, new Score(10, 2), new Score(19, 0)),
            Arguments.of(miss, new Score(10, 1), new Score(14, 0))
        );
    }
}
