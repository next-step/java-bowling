package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class NormalFrameTest {

    public static Stream<Arguments> shotResultAndIsOverProvider() {
        return Stream.of(
            Arguments.of(
                Arrays.asList(10), true
            ),
            Arguments.of(
                Arrays.asList(5), false
            ),
            Arguments.of(
                Arrays.asList(5, 5), true
            ),
            Arguments.of(
                Arrays.asList(0, 10), true
            )
        );
    }

    @MethodSource("shotResultAndIsOverProvider")
    @ParameterizedTest
    @DisplayName("프레임이 끝난는지 판단하는 테스트")
    public void checkOver(List<Integer> shotResults, boolean isOver) {
        NormalFrame frame = new NormalFrame();

        for (int shotResult : shotResults) {
            frame.record(shotResult);
        }

        assertThat(frame.isOver()).isEqualTo(isOver);
    }

    public static Stream<Arguments> shotResultAndFrameResultProvider() {
        return Stream.of(
            Arguments.of(
                Arrays.asList(10), FrameResult.STRIKE
            ),
            Arguments.of(
                Arrays.asList(0, 10), FrameResult.SPARE
            ),
            Arguments.of(
                Arrays.asList(4, 6), FrameResult.SPARE
            ),
            Arguments.of(
                Arrays.asList(4, 3), FrameResult.MISS
            ),
            Arguments.of(
                Arrays.asList(0, 0), FrameResult.GUTTER
            )
        );
    }

    @MethodSource("shotResultAndFrameResultProvider")
    @ParameterizedTest
    @DisplayName("프레임 결과 확인 테스트")
    public void getFrameResult(List<Integer> shotResults, FrameResult frameResult) {
        NormalFrame frame = new NormalFrame();

        for (int shotResult : shotResults) {
            frame.record(shotResult);
        }

        assertThat(frame.getResult()).isEqualTo(frameResult);
    }
}