package bowling.utils;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("FrameViewUtil Test")
class FrameViewUtilTest {

    static Stream<Arguments> provideFinalFrameView() {
        return Stream.of(
                Arguments.of(new int[]{10, 10, 10}, Arrays.asList("X", "X", "X")),
                Arguments.of(new int[]{10, 10, 9}, Arrays.asList("X", "X", "9")),
                Arguments.of(new int[]{10, 10, 0}, Arrays.asList("X", "X", "-")),
                Arguments.of(new int[]{10, 0, 0}, Arrays.asList("X", "-", "-")),
                Arguments.of(new int[]{10, 0, 10}, Arrays.asList("X", "-", "/")),
                Arguments.of(new int[]{10, 9, 1}, Arrays.asList("X", "9", "/")),
                Arguments.of(new int[]{9, 1, 0}, Arrays.asList("9", "/", "-")),
                Arguments.of(new int[]{9, 1, 9}, Arrays.asList("9", "/", "9")),
                Arguments.of(new int[]{9, 1, 10}, Arrays.asList("9", "/", "X")),
                Arguments.of(new int[]{0, 10, 10}, Arrays.asList("-", "/", "X"))
        );
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("provideFinalFrameView")
    @DisplayName("FinalFrame 출력값 테스트")
    void outputFinalFrame(int[] pins, List<String> expected) {
        //given
        Frame frame = FinalFrame.of(pins);

        //when
        List<String> actual = FrameViewUtil.show(frame);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> provideNormalFrameView() {
        return Stream.of(
                Arguments.of(new int[]{9, 1}, Arrays.asList("9", "/")),
                Arguments.of(new int[]{0, 10}, Arrays.asList("-", "/")),
                Arguments.of(new int[]{0, 5}, Arrays.asList("-", "5")),
                Arguments.of(new int[]{0, 0}, Arrays.asList("-", "-")),
                Arguments.of(new int[]{9, 0}, Arrays.asList("9", "-")),
                Arguments.of(new int[]{5, 2}, Arrays.asList("5", "2")),
                Arguments.of(new int[]{10}, Collections.singletonList("X")),
                Arguments.of(new int[]{9}, Collections.singletonList("9")),
                Arguments.of(new int[]{1}, Collections.singletonList("1")),
                Arguments.of(new int[]{0}, Collections.singletonList("-"))
        );
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("provideNormalFrameView")
    @DisplayName("NormalFrame 출력값 테스트")
    void outputNormalFrame(int[] pins, List<String> expected) {
        //given
        Frame frame = NormalFrame.of(1, pins);

        //when
        List<String> actual = FrameViewUtil.show(frame);

        //then
        assertThat(actual).isEqualTo(expected);
    }
}