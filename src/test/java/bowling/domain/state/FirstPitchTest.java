package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.domain.score.exception.DoNotCalculateException;
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

public class FirstPitchTest {

    @Test
    @DisplayName("FirstPitch 클래스 객체 생성")
    void create() {
        assertThat(Ready.of(5)).isInstanceOf(FirstPitch.class);
    }

    @Test
    @DisplayName("이전 프레임 점수 계산")
    void calculateScore() {
        Score strikeScore = new Strike(10).createScore();
        Score currentScore = Ready.of(5).calculateScore(strikeScore);

        assertAll(() -> assertThat(currentScore.left()).isEqualTo(1),
                () -> assertThatThrownBy(() -> currentScore.getScore()).isExactlyInstanceOf(DoNotCalculateException.class));
    }

    @Test
    @DisplayName("보너스 투구 시, 예외 처리")
    void bonusBowlingException() {
        assertThatThrownBy(() -> new FirstPitch(5).bonusBowling(5)).isExactlyInstanceOf(BonusBowlingException.class);
    }

    @ParameterizedTest(name = "현재 및 다음 프레임의 투구 결과 상태 - {1}")
    @MethodSource("bowlingCondition")
    <T extends State> void bowling(int pins, Class<T> clazz) {
        assertThat(Ready.of(pins).bowling(pins)).isInstanceOf(clazz);
    }

    private static Stream<Arguments> bowlingCondition() {
        return Stream.of(
                Arguments.of(5, Spare.class),
                Arguments.of(4, Miss.class),
                Arguments.of(0, Gutter.class)
        );
    }
}
