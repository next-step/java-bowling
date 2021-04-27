package bowling;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {


    @Test
    public void nextFrame_4번째_프레임() {
        // given
        List<Integer> scores = Arrays.asList(1, 5, 3, 4, 2, 3);

        IntStream.range(0, 9)
                .forEach(i -> frameList.add(normalFrame));
        Frames frames = new Frames();
        int expectNextFrame = 9;
        // when

        // then
        assertThat(frames.nextFrame()).isEqualTo(expectNextFrame);
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
        int expectNextFrame = 5;
        // when

        // then
        assertThat(frames.nextFrame()).isEqualTo(expectNextFrame);
    }


    @Test
    public void addScore_10번째_프레임() throws Exception {
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
        assertThat(resultFrames).isEqualTo(expectFrames);
    }

    @Test
    public void addScore_6번째_프레임() throws Exception {
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
        System.out.println(resultFrames);
        System.out.println(expectFrames);
        // then
        assertThat(resultFrames).isEqualTo(expectFrames);
    }


    @Test
    public void addScore_5번째_프레임() throws Exception {
        // given
        int ADD_SCORE = 10;
        Frames frames = new Frames();
        Frames expectFrames = new Frames();

        // when
        Frames resultFrames = frames.addScore(ADD_SCORE);

        System.out.println(resultFrames);
        System.out.println(expectFrames);
        // then
        assertThat(resultFrames).isEqualTo(expectFrames);
    }


}
