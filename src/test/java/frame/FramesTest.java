package frame;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    private static Stream<Arguments> getFrames() {
        return Stream.of(
                Arguments.of(new ArrayList<>(), 1),
                Arguments.of(Arrays.asList(aMockNormalFrame(), aMockNormalFrame()), 3)
        );
    }

    private static NormalFrame aMockNormalFrame() {
        return new NormalFrame(1, new ArrayList<>());
    }

    @ParameterizedTest
    @MethodSource("getFrames")
    void getNextFrameNumber(List<Frame> allFrames, int number) {
        Frames frames = new Frames(allFrames);
        assertThat(frames.getNextFrameNumber()).isEqualTo(number);
    }
}