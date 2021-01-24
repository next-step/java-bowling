package bowling.domain.frame;

import bowling.bowlingexception.InvalidScoreCalculationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LastFrameTest {

    @Test
    @DisplayName("일반적인 점수 입력")
    void recordScore() {
        LastFrame frame = new LastFrame();

        frame.record(9);
    }

    @Test
    @DisplayName("첫 2회 시도가 MISS일 때의 종료 시나리오")
    void endConditionWhenMiss() {
        LastFrame frame = new LastFrame();

        assertThat(frame.isEnd()).isFalse();
        frame.record(4);
        assertThat(frame.isEnd()).isFalse();
        frame.record(5);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("Strike 이후 추가 2회 종료 시나리오")
    void endConditionWithStrike() {
        LastFrame frame = new LastFrame();

        assertThat(frame.isEnd()).isFalse();
        frame.record(10);
        assertThat(frame.isEnd()).isFalse();
        frame.record(5);
        assertThat(frame.isEnd()).isFalse();
        frame.record(4);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("Spare 이후 추가 2회 종료 시나리오")
    void endConditionWithSpare() {
        LastFrame frame = new LastFrame();

        assertThat(frame.isEnd()).isFalse();
        frame.record(4);
        assertThat(frame.isEnd()).isFalse();
        frame.record(6);
        assertThat(frame.isEnd()).isFalse();
        frame.record(9);
        assertThat(frame.isEnd()).isTrue();
    }

    private static Stream<Arguments> oneRecordScenario() {
        return Stream.of(
                Arguments.of(0, "-"),
                Arguments.of(1, "1"),
                Arguments.of(9, "9"),
                Arguments.of(10, "X")
        );
    }

    private static Stream<Arguments> twoRecordScenario() {
        return Stream.of(
                Arguments.of(0, 10, "- | /"),
                Arguments.of(1, 8, "1 | 8"),
                Arguments.of(0, 0, "- | -"),
                Arguments.of(10, 10, "X | X"),
                Arguments.of(10, 7, "X | 7"),
                Arguments.of(10, 0, "X | -")
        );
    }

    private static Stream<Arguments> thirdRecordScenario() {
        return Stream.of(
                Arguments.of(0, 10, 0, "- | / | -"),
                Arguments.of(0, 10, 4, "- | / | 4"),
                Arguments.of(0, 10, 10, "- | / | X"),
                Arguments.of(1, 9, 0, "1 | / | -"),
                Arguments.of(10, 10, 0, "X | X | -"),
                Arguments.of(10, 6, 4, "X | 6 | /"),
                Arguments.of(10, 10, 10, "X | X | X")
        );
    }

    @ParameterizedTest
    @MethodSource("oneRecordScenario")
    @DisplayName("1회차 시도 이후 출력")
    void descriptionAfterFirstPitch(int firstPitch, String expected) {
        LastFrame frame = new LastFrame();
        frame.record(firstPitch);

        assertThat(frame.getDescriptionForm()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("twoRecordScenario")
    @DisplayName("2회차 시도 이후 출력")
    void descriptionAfterSecondPitch(int firstPitch, int secondPitch, String expected) {
        LastFrame frame = new LastFrame();
        frame.record(firstPitch);
        frame.record(secondPitch);

        assertThat(frame.getDescriptionForm()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("thirdRecordScenario")
    @DisplayName("2회차 시도 이후 출력")
    void descriptionAfterThirdPitch(int firstPitch, int secondPitch, int thirdPitch, String expected) {
        LastFrame frame = new LastFrame();
        frame.record(firstPitch);
        frame.record(secondPitch);
        frame.record(thirdPitch);

        assertThat(frame.getDescriptionForm()).isEqualTo(expected);
    }

    @Test
    @DisplayName("프레임이 종료되지 않은 상태일 때의 스코어 반환 요청 예외처리")
    void scoringWhenItIsCannotBeCalculated() {
        LastFrame lastFrame = new LastFrame();
        assertThatThrownBy(
                lastFrame::calculateScore
        ).isInstanceOf(InvalidScoreCalculationException.class);
    }
}
