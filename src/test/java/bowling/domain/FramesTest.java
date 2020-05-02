package bowling.domain;

import bowling.domain.frame.Frame;
import org.assertj.core.api.IterableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

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

    @ParameterizedTest
    @CsvSource(value = {
            "5,5,10:3:2,1,0"
    }, delimiter = ':')
    void shot(String inputShots, int frameSize, String expectShotSizes) {
        Frames frames = new Frames();
        splitInts(inputShots)
                .forEach(frames::shot);

        IterableAssert<Frame> assertFrames = assertThat(frames.getFrames())
                .hasSize(frameSize);
        splitInts(expectShotSizes)
                .forEach(size ->
                        assertFrames.anyMatch(v -> v.getShotsCount() == size));
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
    }, delimiter = ':')
    void getScores(String inputShots, String expectScores) {
        Frames frames = new Frames();
        splitInts(inputShots)
                .forEach(frames::shot);
        assertThat(frames.getScores())
                .isEqualTo(splitInts(expectScores));
    }

    private List<Integer> splitInts(String splittableString) {
        return Arrays.stream(splittableString.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
