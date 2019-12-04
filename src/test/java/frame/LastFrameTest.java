package frame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import score.framescore.FrameScore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LastFrameTest {

    private static Stream<Arguments> getLastBowling() {
        return Stream.of(
                Arguments.of(Arrays.asList(1), false),
                Arguments.of(Arrays.asList(10), false),
                Arguments.of(Arrays.asList(10, 10), false),
                Arguments.of(Arrays.asList(10, 10, 10), true),
                Arguments.of(Arrays.asList(1, 1), true),
                Arguments.of(Arrays.asList(1, 9), false),
                Arguments.of(Arrays.asList(1, 9, 10), true),
                Arguments.of(Arrays.asList(10, 9), false),
                Arguments.of(Arrays.asList(10, 9, 1), true)
        );
    }

    @ParameterizedTest
    @MethodSource("getLastBowling")
    void isFull(List<Integer> scores, boolean answer) {
        LastFrame lastFrame = LastFrame.init();
        for (int score : scores) {
            lastFrame.bowling(score);
        }
        assertThat(lastFrame.isFull()).isEqualTo(answer);
        assertThat(lastFrame.isNotFull()).isNotEqualTo(answer);
    }


    @Test
    void addNextScore() {
        //given
        NormalFrame normalFrame = new NormalFrame(9, new ArrayList<>());
        normalFrame.bowling(10);

        Frame lastFrame = normalFrame.getLastFrame();
        assertThat(normalFrame.getFrameScore()).isEqualTo(new FrameScore(10, 2));

        lastFrame.bowling(10);
        assertThat(normalFrame.getFrameScore()).isEqualTo(new FrameScore(20, 1));

        lastFrame.bowling(10);
        assertThat(normalFrame.getFrameScore()).isEqualTo(new FrameScore(30, 0));
    }

    @Test
    void getFrameScore() {
        LastFrame lastFrame = LastFrame.init();

        assertThat(lastFrame.getFrameScore()).isEqualTo(new FrameScore(0, 1));
        lastFrame.bowling(10);
        assertThat(lastFrame.getFrameScore()).isEqualTo(new FrameScore(10, 1));

        lastFrame.bowling(1);
        assertThat(lastFrame.getFrameScore()).isEqualTo(new FrameScore(11, 0));

        lastFrame.bowling(9);
        assertThat(lastFrame.getFrameScore()).isEqualTo(new FrameScore(20, 0));
    }
}