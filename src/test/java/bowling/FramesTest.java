package bowling;

import bowling.domain.frame.Frames;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class FramesTest {

    private static Stream<Arguments> nowFrameArguments() {
        return Stream.of(
                arguments(Arrays.asList(1, 5, 3, 4, 2, 3), 3),
                arguments(Arrays.asList(1, 9, 10, 10, 10, 10), 5),
                arguments(Arrays.asList(1, 5, 3, 4, 2, 3, 10, 10, 10, 10, 10, 10, 10, 1), 9),
                arguments(Arrays.asList(), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("nowFrameArguments")
    public void nowFrame(List<Integer> scores, int expectNowFrame) throws Exception {
        // given
        Frames frames = new Frames();
        for (int score : scores) {
            frames = frames.addScore(score);
        }

        // when

        // then
        assertThat(frames.nowFrame()).isEqualTo(expectNowFrame);
    }
//
//    @Test
//    public void addScore_10번째_프레임() throws Exception {
//        // given
//        int ADD_SCORE = 3;
//        Frame normalFrame = NormalFrame.valueOf(Arrays.asList(3, 4));
//        List<Frame> frameList = new ArrayList<>();
//        IntStream.range(0, 9)
//                .forEach(i -> frameList.add(normalFrame));
//        Frames frames = new Frames(frameList);
//        frameList.add(FinalFrame.valueOf(ADD_SCORE));
//        Frames expectFrames = new Frames(frameList);
//        // when
//        Frames resultFrames = frames.addScore(ADD_SCORE);
//
//        // then
//        assertThat(resultFrames).isEqualTo(expectFrames);
//    }
//
//    @Test
//    public void addScore_6번째_프레임() throws Exception {
//        // given
//        int ADD_SCORE = 3;
//        Frame normalFrame = NormalFrame.valueOf(Arrays.asList(3, 4));
//        List<Frame> frameList = new ArrayList<>();
//        IntStream.range(0, 5)
//                .forEach(i -> frameList.add(normalFrame));
//        frameList.add(NormalFrame.valueOf(3));
//        Frames frames = new Frames(frameList);
//        frameList.get(5).addScore(ADD_SCORE);
//        Frames expectFrames = new Frames(frameList);
//
//        // when
//        Frames resultFrames = frames.addScore(ADD_SCORE);
//        System.out.println(resultFrames);
//        System.out.println(expectFrames);
//        // then
//        assertThat(resultFrames).isEqualTo(expectFrames);
//    }
//
//
//    @Test
//    public void addScore_5번째_프레임() throws Exception {
//        // given
//        int ADD_SCORE = 10;
//        Frames frames = new Frames();
//        Frames expectFrames = new Frames();
//
//        // when
//        Frames resultFrames = frames.addScore(ADD_SCORE);
//
//        System.out.println(resultFrames);
//        System.out.println(expectFrames);
//        // then
//        assertThat(resultFrames).isEqualTo(expectFrames);
//    }


}
