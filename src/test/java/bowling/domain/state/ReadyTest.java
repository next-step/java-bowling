package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.domain.state.exception.BonusBowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ReadyTest {

    @ParameterizedTest(name = "현재 및 다음 프레임의 투구 결과 상태 - {1}")
    @MethodSource("bowlingCondition")
    <T extends State> void bowling(int pins, Class<T> clazz) {
        assertAll(() -> assertThat(Ready.of(pins)).isInstanceOf(clazz),
                () -> assertThat(new Ready().bowling(pins)).isInstanceOf(clazz));
    }

    private static Stream<Arguments> bowlingCondition() {
        return Stream.of(
                Arguments.of(10, Strike.class),
                Arguments.of(5, FirstPitch.class),
                Arguments.of(-1, Ready.class)
        );
    }

    @Test
    @DisplayName("이전 프레임 점수 계산 시, left 초기화")
    void calculateScore() {
        Score strikeScore = new Strike(10).createScore();
        Score currentScore = new Ready().calculateScore(strikeScore);

        assertAll(() -> assertThat(currentScore.getScore()).isEqualTo(10),
                () -> assertThat(currentScore.left()).isEqualTo(0));
    }

    @Test
    @DisplayName("보너스 투구 시, 예외 처리")
    void bonusBowlingException() {
        assertThatThrownBy(() -> new Ready().bonusBowling(0)).isExactlyInstanceOf(BonusBowlingException.class);
    }
}
