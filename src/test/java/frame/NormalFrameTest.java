package frame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import score.ScoreInfo;
import score.Status;
import score.framescore.FrameScore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @Test
    void 첫번째_프레임만들기() {
        NormalFrame firstFrame = NormalFrame.firstNormalFrame();

        assertThat(firstFrame).isEqualTo(new NormalFrame(1, new ArrayList<>()));
    }

    @Test
    void nextNormalFrame() {
        NormalFrame firstFrame = NormalFrame.firstNormalFrame();

        assertThat(firstFrame.nextFrame()).isEqualTo(new NormalFrame(2, new ArrayList<>()));
    }

    @Test
    void isFull_일반프레임_두번던지거나_스트라이크면넘어가기() {
        NormalFrame normalFrame = NormalFrame.firstNormalFrame();
        assertThat(normalFrame.isFull()).isFalse();

        normalFrame.bowling(1);
        assertThat(normalFrame.isFull()).isFalse();

        normalFrame.bowling(1);
        assertThat(normalFrame.isFull()).isTrue();

        NormalFrame strikeFrame = NormalFrame.firstNormalFrame();
        strikeFrame.bowling(10);
        assertThat(strikeFrame.isFull()).isTrue();
    }

    private static Stream<Arguments> sourceForAddNextScore() {
        NormalFrame emptyFrame = NormalFrame.firstNormalFrame();

        NormalFrame normalFrame = new NormalFrame(1, Arrays.asList(new ScoreInfo(1, Status.MISS)));

        NormalFrame fullFrame = new NormalFrame(1, Arrays.asList(new ScoreInfo(1, Status.MISS), new ScoreInfo(5, Status.MISS)));

        NormalFrame hasNextFrame = new NormalFrame(2, Arrays.asList(new ScoreInfo(1, Status.MISS)));
        Frame frame = hasNextFrame.nextFrame();
        frame.bowling(1);

        return Stream.of(
                Arguments.of(emptyFrame, new FrameScore(1, 0), new FrameScore(1, 0)),
                Arguments.of(emptyFrame, new FrameScore(1, 1), new FrameScore(1, 1)),
                Arguments.of(normalFrame, new FrameScore(1, 1), new FrameScore(2, 0)),
                Arguments.of(normalFrame, new FrameScore(1, 2), new FrameScore(2, 1)),
                Arguments.of(fullFrame, new FrameScore(1, 2), new FrameScore(7, 0)),
                Arguments.of(hasNextFrame, new FrameScore(1, 2), new FrameScore(3, 0))
        );
    }

    @ParameterizedTest
    @MethodSource("sourceForAddNextScore")
    void addNextScore(NormalFrame normalFrame, FrameScore before, FrameScore answer) {
        FrameScore added = normalFrame.addNextScore(before);

        assertThat(added).isEqualTo(answer);
    }

    //@Override
    //    public FrameScore getFrameScore() {
    //        FrameScore frameScore = makeFrameScore();
    //
    //        if (frameScore.canAdd()) {
    //            return frameInfo.calculateNext(frameScore);
    //        }
    //
    //        return frameScore;
    //    }
    //
    //    private FrameScore makeFrameScore() {
    //        if (isNotFull()) {
    //            return new FrameScore(scores.getSum(), 1);
    //        }
    //
    //        Status status = scores.getStatus();
    //        return new FrameScore(scores.getSum(), status.getAddCount());
    //    }

    @Test
    void getFrameScore() {
        NormalFrame normalFrame = NormalFrame.firstNormalFrame();
        assertThat(normalFrame.getFrameScore()).isEqualTo(new FrameScore(0, 1));

        normalFrame.bowling(1);
        assertThat(normalFrame.getFrameScore()).isEqualTo(new FrameScore(1, 1));

        normalFrame.bowling(1);
        assertThat(normalFrame.getFrameScore()).isEqualTo(new FrameScore(2, 0));

        Frame nextFrame = normalFrame.nextFrame();
        nextFrame.bowling(10);
        assertThat(nextFrame.getFrameScore()).isEqualTo(new FrameScore(10, 2));

        Frame nextOfNextFrame = nextFrame.nextFrame();
        nextOfNextFrame.bowling(1);
        assertThat(nextFrame.getFrameScore()).isEqualTo(new FrameScore(11, 1));

        nextOfNextFrame.bowling(5);
        assertThat(nextFrame.getFrameScore()).isEqualTo(new FrameScore(16, 0));
    }
}