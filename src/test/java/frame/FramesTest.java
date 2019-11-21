package frame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import score.ScoreInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    private static Stream<Arguments> getFrames() {
        return Stream.of(
                Arguments.of(new ArrayList<>(), 1),
                Arguments.of(Arrays.asList(aMockNormalFrame(), aMockNormalFrame()), 2)
        );
    }

    private static NormalFrame aMockNormalFrame() {
        return new NormalFrame(1, new ArrayList<>());
    }


    @ParameterizedTest
    @MethodSource("getFrames")
    void getNextFrameNumber(List<Frame> allFrames, int number) {
        Frames frames = new Frames(allFrames);
        assertThat(frames.getNowFrameNumber()).isEqualTo(number);
    }

    @Test
    void getNowFrame() {
        Frames frames = new Frames(new ArrayList<>());
        Frame nowFrame = frames.getNowFrame();

        assertThat(nowFrame).isEqualTo(new NormalFrame(1, new ArrayList<>()));

        nowFrame.bowling(1);
        nowFrame = frames.getNowFrame();

        assertThat(nowFrame).isEqualTo(new NormalFrame(1, Arrays.asList(ScoreInfo.firstScore(1))));

        nowFrame.bowling(1);
        nowFrame = frames.getNowFrame();

        assertThat(nowFrame).isEqualTo(new NormalFrame(2, new ArrayList<>()));
    }
}