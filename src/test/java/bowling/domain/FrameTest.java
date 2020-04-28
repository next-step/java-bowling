package bowling.domain;

import bowling.domain.scoreType.ScoreType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class FrameTest {

    @Test
    void of() {
        assertThatCode(() -> Frame.normalFrame())
                .doesNotThrowAnyException();
    }

    @Test
    void shot() {
        Frame normalFrame = Frame.normalFrame();
        normalFrame.shot(4);
        normalFrame.shot(6);
        normalFrame.shot(5);

        assertThat(normalFrame.shotScores())
                .anyMatch(ShotScore.init(4)::equals)
                .anyMatch(ShotScore.init(4).next(6)::equals);

        assertThat(normalFrame.shotScores())
                .hasSize(2);

        assertThatThrownBy(() -> normalFrame.shot(5))
                .isInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"5,4", "10"})
    void isClosed(String shotString) {
        Frame normalFrame = Frame.normalFrame();
        assertThat(normalFrame.isFrameSet())
                .isFalse();

        int[] shots = splitInts(shotString);
        for (int shot : shots) {
            normalFrame.shot(shot);
        }

        assertThat(normalFrame.isFrameSet())
                .isTrue();
    }

    @Test
    void shotLastFrame() {
        Frame finalFrame = Frame.lastFrame();
        finalFrame.shot(4);
        assertThat(finalFrame.shotScores().stream())
                .anyMatch(v -> ScoreType.MISS_FIRST.equals(v.scoreType()))
                .anyMatch(v -> v.singleScore() == 4);

        finalFrame.shot(6);
        assertThat(finalFrame.shotScores().stream())
                .anyMatch(v -> ScoreType.SPARE.equals(v.scoreType()))
                .anyMatch(v -> v.singleScore() == 6);

        finalFrame.shot(5);
        assertThat(finalFrame.getFrameScore().getScore())
                .isEqualTo(15);

        assertThatThrownBy(() -> finalFrame.shot(5))
                .isInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"5,4", "10,10,10", "4,6,4", "10,4,5"})
    void isClosedLastFrame(String shotString) {
        int[] shots = splitInts(shotString);
        Frame finalFrame = Frame.lastFrame();
        assertThat(finalFrame.isFrameSet())
                .isFalse();

        for (int shot : shots) {
            finalFrame.shot(shot);
        }

        assertThat(finalFrame.isFrameSet())
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"5", "10,10", "4,6", "10,4"})
    void isNotClosedFrame(String shotString) {
        int[] shots = splitInts(shotString);
        Frame finalFrame = Frame.lastFrame();
        assertThat(finalFrame.isFrameSet())
                .isFalse();

        for (int i = 0; i < shots.length; i++) {
            finalFrame.shot(shots[i]);
        }

        assertThat(finalFrame.isFrameSet())
                .isFalse();
    }

    private int[] splitInts(String shotString) {
        return Arrays.stream(shotString.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "4,4:8",
            "4,6,10:20",
            "10,10,10:30",
            "10,4,3:17"
    }, delimiter = ':')
    void getFrameScore(String shotString, int expectScore) {
        int[] shots = splitInts(shotString);
        Frame frame = Frame.normalFrame();
        for (int shot : shots) {
            frame.shot(shot);
        }
        assertThat(frame.getFrameScore().getScore())
                .isEqualTo(expectScore);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "10,4,3",
            "4,3",
            "1,9,2",
            "10,10,3"
    })
    void isScoreCalculated(String shotString) {
        int[] shots = splitInts(shotString);
        Frame frame = Frame.normalFrame();
        for (int shot : shots) {
            frame.shot(shot);
        }
        assertThat(frame.isScoreCalculated())
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "10,4",
            "4",
            "1,9",
            "10,10"
    })
    void isScoreNotCalculated(String shotString) {
        int[] shots = splitInts(shotString);
        Frame frame = Frame.normalFrame();
        for (int shot : shots) {
            frame.shot(shot);
        }
        assertThat(frame.isScoreCalculated())
                .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "10,4,3",
            "4,3",
            "1,9,2",
            "10,10,3"
    })
    void isScoreCalculatedLastFrame(String shotString) {
        int[] shots = splitInts(shotString);
        Frame finalFrame = Frame.lastFrame();
        for (int shot : shots) {
            finalFrame.shot(shot);
        }
        assertThat(finalFrame.isScoreCalculated())
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "10,4",
            "4",
            "1,9",
            "10,10"
    })
    void isNotScoreCalculatedLastFrame(String shotString) {
        int[] shots = splitInts(shotString);
        Frame finalFrame = Frame.lastFrame();
        for (int shot : shots) {
            finalFrame.shot(shot);
        }
        assertThat(finalFrame.isScoreCalculated())
                .isFalse();
    }

}
