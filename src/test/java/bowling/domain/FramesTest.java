package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

class FramesTest {

    @Test
    void getCurrentFrameNumber() {
        Frames frames = new Frames();
        assertThat(frames.getCurrentFrameNumber())
                .isEqualTo(1);

        frames.shot(10);
        assertThat(frames.getCurrentFrameNumber())
                .isEqualTo(2);

        frames.shot(5);
        assertThat(frames.getCurrentFrameNumber())
                .isEqualTo(2);
    }

    @Test
    void shot() {
        Frames frames = new Frames();
        frames.shot(5);
        frames.shot(5);
        frames.shot(10);

        assertThat(frames.getFrames())
                .hasSize(2)
                .anyMatch(v -> v.isFrameSet() &&
                        v.getFrameScore().getScore() == 20 &&
                        v.shotScores().size() == 2)
                .anyMatch(v -> v.isFrameSet() &&
                        !v.getFrameScore().isCalculated());
    }

    @Test
    void isGameSet() {
        Frames frames = new Frames();
        for (int i = 0; i < 10; i++) {
            frames.shot(10);
        }
        assertThat(frames.isGameSet())
                .isFalse();
        frames.shot(4);
        frames.shot(5);
        assertThat(frames.isGameSet())
                .isTrue();

        assertThat(frames.getFrames())
                .hasSize(10);
    }

    @Test
    void getCurrentFrameShotCount() {
        Frames frames = new Frames();
        assertThat(frames.getCurrentFrameShotCount())
                .isEqualTo(0);
        frames.shot(4);
        assertThat(frames.getCurrentFrameShotCount())
                .isEqualTo(1);

        frames.shot(2);
        assertThat(frames.getCurrentFrameShotCount())
                .isEqualTo(0);

        frames.shot(10);
        assertThat(frames.getCurrentFrameShotCount())
                .isEqualTo(0);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "4,5:9",
            "10,10,10:30",
            "10,9,1,4,3:20,14,7",
    },delimiter = ':')
    void getScores(String inputShots, String expectScores){
        Frames frames = new Frames();
        splitInts(inputShots)
                .forEach(frames::shot);
        assertThat(frames.getScores())
                .isEqualTo(splitInts(expectScores));
    }

    private List<Integer> splitInts(String splittableString){
        return Arrays.stream(splittableString.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
