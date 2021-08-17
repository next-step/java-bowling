package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import bowling.domain.frame.exception.FramesSizeException;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("프레임들")
class FramesTest {

    private static final Random RANDOM = new Random();

    private static final Function<Integer, List<Frame>> FRAME_GENERATOR = (size) -> IntStream.range(0, size)
        .mapToObj(i -> Frame.of(PitchResult.of(RANDOM.nextInt(10))))
        .collect(Collectors.toList());

    public static Stream<Arguments> frames() {
        return Stream.of(
            Arguments.of(FRAME_GENERATOR.apply(1)),
            Arguments.of(FRAME_GENERATOR.apply(10)));
    }

    @DisplayName("[성공] 생성")
    @ParameterizedTest
    @MethodSource("frames")
    public void create(final List<Frame> frames) {
        // given

        // when
        final Frames newFrames = Frames.of(frames);

        // then
        assertThat(newFrames).isNotNull();
    }

    public static Stream<Arguments> invalidFrames() {
        return Stream.of(
            Arguments.of(FRAME_GENERATOR.apply(11)));
    }

    @DisplayName("[실패] 생성 - 유효하지 않은 프레임 사이즈")
    @ParameterizedTest
    @MethodSource("invalidFrames")
    public void create_invalidSize(final List<Frame> frames) {
        // given

        // when
        assertThrows(FramesSizeException.class, () -> Frames.of(frames));

        // then
    }

    @DisplayName("[성공] 프레임 추가")
    @Test
    public void add() {
        // given
        final int size = 5;
        final List<Frame> frames = FRAME_GENERATOR.apply(size);

        // when
        frames.add(Frame.of(PitchResult.of(1)));

        // then
        assertThat(frames).hasSize(size + 1);
    }

    @DisplayName("[실패] 프레임 추가 - 유효하지 않은 프레임 사이즈")
    @Test
    public void add_invalidSize() {
        // given
        final int size = 10;
        final List<Frame> frames = FRAME_GENERATOR.apply(size);

        // when
        assertThrows(FramesSizeException.class, () -> frames.add(Frame.of(PitchResult.of(1))));

        // then
    }
}
