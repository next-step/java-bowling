package bowling;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class FramesTest {

    @Test
    public void nextFrame_10번째_프레임() {
        // given
        Frame normalFrame = NormalFrame.valueOf(Arrays.asList(3, 4));
        List<Frame> frameList = new ArrayList<>();
        IntStream.range(0, 9)
                .forEach(i -> frameList.add(normalFrame));
        Frames frames = new Frames(frameList);
        int expectNextFrame = 10;
        // when

        // then
        Assertions.assertThat(frames.nextFrame()).isEqualTo(expectNextFrame);
    }

    @Test
    public void nextFrame_6번째_프레임() {
        // given
        Frame normalFrame = NormalFrame.valueOf(Arrays.asList(3, 4));
        List<Frame> frameList = new ArrayList<>();
        IntStream.range(0, 5)
                .forEach(i -> frameList.add(normalFrame));
        frameList.add(NormalFrame.valueOf(3));
        Frames frames = new Frames(frameList);
        int expectNextFrame = 6;
        // when

        // then
        Assertions.assertThat(frames.nextFrame()).isEqualTo(expectNextFrame);
    }

    @Test
    public void addFrame_10번째_프레임() {
        // given
        int ADD_SCORE = 3;
        Frame normalFrame = NormalFrame.valueOf(Arrays.asList(3, 4));
        List<Frame> frameList = new ArrayList<>();
        IntStream.range(0, 9)
                .forEach(i -> frameList.add(normalFrame));
        Frames frames = new Frames(frameList);
        frameList.add(FinalFrame.valueOf(ADD_SCORE));
        Frames expectFrames = new Frames(frameList);
        // when
        Frames resultFrames = frames.addScore(ADD_SCORE);

        // then
        Assertions.assertThat(resultFrames).isEqualTo(expectFrames);
    }

    @Test
    public void addFrame_6번째_프레임() throws Exception {
        // given
        int ADD_SCORE = 3;
        Frame normalFrame = NormalFrame.valueOf(Arrays.asList(3, 4));
        List<Frame> frameList = new ArrayList<>();
        IntStream.range(0, 5)
                .forEach(i -> frameList.add(normalFrame));
        frameList.add(NormalFrame.valueOf(3));
        Frames frames = new Frames(frameList);
        frameList.get(5).addScore(ADD_SCORE);
        Frames expectFrames = new Frames(frameList);

        // when
        Frames resultFrames = frames.addScore(ADD_SCORE);

        // then
        Assertions.assertThat(resultFrames).isEqualTo(expectFrames);
    }

}
