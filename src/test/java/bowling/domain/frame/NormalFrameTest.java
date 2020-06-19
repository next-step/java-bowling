package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFrameTest {

    private Frame createFrame() {
        return new NormalFrame();
    }

    @ParameterizedTest
    @MethodSource("provideCountAndAvailablePlay")
    @DisplayName("동일 frame에 값을 넣을 수 있는지 확인")
    void availablePlayTest(List<Integer> points, boolean availablePlay) {
        Frame frame = createFrame();
        for (Integer point : points) {
            frame.addPoint(point);
        }
        assertThat(frame.availablePlay()).isEqualTo(availablePlay);
    }

    private static Stream<Arguments> provideCountAndAvailablePlay() {
        return Stream.of(
                Arguments.of(Arrays.asList(10), false),
                Arguments.of(Arrays.asList(5, 5), false),
                Arguments.of(Arrays.asList(0, 0), false),
                Arguments.of(Arrays.asList(5, 4), false),
                Arguments.of(Arrays.asList(4), true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideOverflowScore")
    @DisplayName("총 점수 합산이 10이 넘는 경우 Exception")
    void validateScoreTest(int firstPoint, int secondPoint) {
        Frame frame = createFrame();
        frame.addPoint(firstPoint);
        assertThatThrownBy(() -> frame.addPoint(secondPoint))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> provideOverflowScore() {
        return Stream.of(
                Arguments.of(5, 6),
                Arguments.of(1, 10)
        );
    }
}