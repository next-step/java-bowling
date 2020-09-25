package bowling.domain.frame;

import bowling.domain.score.bonus.BonusScores;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class LastFrameTest {
    private LastFrame lastFrame;

    @BeforeEach
    void setUp() {
        lastFrame = new LastFrame(new BonusScores());
    }

    @DisplayName("플레이 가능 여부 테스트")
    @Test
    void isPlayableTest() {
        lastFrame.addScore(1);
        assertThat(lastFrame.isPlayable()).isTrue();
    }

    @DisplayName("2구 이내 스트라이크/스페어 일 경우 테스트")
    @ParameterizedTest
    @MethodSource("pins")
    void addPointTest(List<Integer> pins) {
        assertThatCode(
                () -> {
                    for (Integer pin : pins) {
                        lastFrame.addScore(pin);
                    }
                }
        );
    }

    static Stream<Arguments> pins() {
        return Stream.of(
                arguments(Arrays.asList(10, 2, 1)),
                arguments(Arrays.asList(1, 9, 10)),
                arguments(Arrays.asList(4, 6, 10))
        );
    }

    @DisplayName("1구가 스트라이크 아닐 경우 1구, 2구 합 예외처리 테스트")
    @ParameterizedTest
    @MethodSource("secondPins")
    void addPointFailedTest(List<Integer> pins) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> {
                    for (Integer pin : pins) {
                        lastFrame.addScore(pin);
                    }
                }
        );
    }

    static Stream<Arguments> secondPins() {
        return Stream.of(
                arguments(Arrays.asList(2, 9)),
                arguments(Arrays.asList(1, 10))
        );
    }

    @DisplayName("3구 가능 상태에서 마지막 스코어 테스트")
    @ParameterizedTest
    @MethodSource("thirdPins")
    void strikeOrSpareTest(List<Integer> pins) {
        for (Integer pin : pins) {
            lastFrame.addScore(pin);
        }

        assertThat(lastFrame.isPlayable()).isTrue();
    }

    static Stream<Arguments> thirdPins() {
        return Stream.of(
                arguments(Arrays.asList(10, 2)),
                arguments(Arrays.asList(1, 9)),
                arguments(Arrays.asList(4, 6))
        );
    }
}