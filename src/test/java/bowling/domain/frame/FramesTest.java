package bowling.domain.frame;

import bowling.exception.InvalidFramesException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class FramesTest {

    @ParameterizedTest
    @MethodSource("framesProvider")
    @DisplayName("Frames의 생성을 확인한다")
    void create(List<Frame> frames) {
        assertThat(Frames.create(frames)).isInstanceOf(Frames.class);
    }

    public static Stream<Arguments> framesProvider() {
        return Stream.of(
                arguments(List.of(
                        NormalFrame.create(),
                        NormalFrame.create(),
                        NormalFrame.create(),
                        NormalFrame.create(),
                        NormalFrame.create(),
                        NormalFrame.create(),
                        NormalFrame.create(),
                        NormalFrame.create(),
                        NormalFrame.create(),
                        LastFrame.create())
                )
        );
    }

    @ParameterizedTest
    @MethodSource("invalidFramesSizeProvider")
    @DisplayName("프레임 갯수가 10개가 아닐 경우 예외를 반환한다")
    void frameSizeValidation(List<Frame> frames) {
        assertThatThrownBy(() -> Frames.create(frames)).isInstanceOf(InvalidFramesException.class);
    }

    public static Stream<Arguments> invalidFramesSizeProvider() {
        return Stream.of(
                arguments(List.of(
                        NormalFrame.create(),
                        NormalFrame.create(),
                        NormalFrame.create(),
                        NormalFrame.create(),
                        NormalFrame.create(),
                        NormalFrame.create(),
                        NormalFrame.create(),
                        NormalFrame.create(),
                        NormalFrame.create())
                )
        );
    }

    @ParameterizedTest
    @MethodSource("invalidFramesOrderProvider")
    @DisplayName("올바르지 않은 프레임 순서라면 예외를 반환한다")
    void frameOrderValidation(List<Frame> frames) {
        assertThatThrownBy(() -> Frames.create(frames)).isInstanceOf(InvalidFramesException.class);
    }

    public static Stream<Arguments> invalidFramesOrderProvider() {
        return Stream.of(
                arguments(List.of(
                        NormalFrame.create(),
                        NormalFrame.create(),
                        NormalFrame.create(),
                        NormalFrame.create(),
                        LastFrame.create(),
                        NormalFrame.create(),
                        NormalFrame.create(),
                        NormalFrame.create(),
                        NormalFrame.create(),
                        NormalFrame.create())
                )
        );
    }
}