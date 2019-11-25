package frame.info;

import frame.Frame;
import frame.NormalFrame;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import score.ScoreInfo;
import score.Status;
import score.framescore.FrameScore;

import java.util.Collections;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FrameInfoTest {

    private static Stream<Arguments> sourceForCalculateNext() {
        return Stream.of(
                Arguments.of(null, 1),
                Arguments.of(new NormalFrame(2, Collections.singletonList(new ScoreInfo(1, Status.MISS))), 2)
        );
    }

    @Test
    void nextNumber() {
        FrameInfo frameInfo = FrameInfo.make(1);
        assertThat(frameInfo.nextNumber()).isEqualTo(2);
    }

    @ParameterizedTest
    @MethodSource("sourceForCalculateNext")
    void calculateNext(Frame nextFrame, int answer) {
        FrameInfo frameInfo = FrameInfo.make(1);
        frameInfo = frameInfo.makeWithNext(nextFrame);
        FrameScore frameScore = frameInfo.calculateNext(new FrameScore(1, 1));

        assertThat(frameScore.getScore()).isEqualTo(answer);
    }
}