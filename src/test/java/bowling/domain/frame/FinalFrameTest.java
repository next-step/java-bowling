package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("FinalFrame Test")
class FinalFrameTest {

    static Stream<Arguments> provideFinishCheckFrame() {
        return Stream.of(
                Arguments.of(new int[]{10, 10, 10}, true),
                Arguments.of(new int[]{10, 5 ,4}, true),
                Arguments.of(new int[]{9, 1, 10}, true),
                Arguments.of(new int[]{10, 10}, false),
                Arguments.of(new int[]{10, 0}, false),
                Arguments.of(new int[]{9, 1}, false),
                Arguments.of(new int[]{5, 4}, true),
                Arguments.of(new int[]{0, 0}, true),
                Arguments.of(new int[]{0, 9}, true),
                Arguments.of(new int[]{0}, false),
                Arguments.of(new int[]{1}, false)

        );
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("provideFinishCheckFrame")
    @DisplayName("완료된 프레임인지 확인한다.")
    void finishFrameCheck(int[] numbers, boolean actual) {
        // given
        Frame frame = FinalFrame.of(numbers);

        // when
        boolean finished = frame.isFinished();

        // then
        assertThat(finished).isEqualTo(actual);
    }
}