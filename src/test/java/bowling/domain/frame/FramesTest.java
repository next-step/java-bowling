package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    private Frames createFrame() {
        return new Frames();
    }

    @ParameterizedTest
    @MethodSource("provideFrameSize")
    @DisplayName("frame 추가")
    void addFrame(int frameSize) {
        Frames frames = createFrame();
        IntStream.range(0, frameSize)
                .forEach(index -> frames.createNextFrame());

        assertThat(frames.getFrameSize()).isEqualTo(frameSize);
    }

    private static Stream<Arguments> provideFrameSize() {
        return Stream.of(
                Arguments.of(5),
                Arguments.of(10),
                Arguments.of(9)
        );
    }
}