package bowling.domain;

import bowling.domain.scoreType.ScoreType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class NormalFrameTest {

    @Test
    void of() {
        assertThatCode(NormalFrame::init)
                .doesNotThrowAnyException();
    }

    @Test
    void shot() {
        NormalFrame normalFrame = NormalFrame.init();
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
        NormalFrame normalFrame = NormalFrame.init();
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
        Frame finalNormalFrame = NormalFrame.init().last();
        finalNormalFrame.shot(4);
        assertThat(finalNormalFrame.shotScores().stream())
                .anyMatch(v -> ScoreType.MISS_FIRST.equals(v.scoreType()))
                .anyMatch(v -> v.singleScore() == 4);

        finalNormalFrame.shot(6);
        assertThat(finalNormalFrame.shotScores().stream())
                .anyMatch(v -> ScoreType.SPARE.equals(v.scoreType()))
                .anyMatch(v -> v.singleScore() == 6);

        finalNormalFrame.shot(5);
        assertThat(finalNormalFrame.getFrameScore().getScore())
                .isEqualTo(15);

        assertThatThrownBy(() -> finalNormalFrame.shot(5))
                .isInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"5,4", "10,10,10", "4,6,4", "10,4,5"})
    void isClosedLastFrame(String shotString) {
        int[] shots = splitInts(shotString);
        Frame finalNormalFrame = NormalFrame.init().last();
        assertThat(finalNormalFrame.isFrameSet())
                .isFalse();

        for (int shot : shots) {
            finalNormalFrame.shot(shot);
        }

        assertThat(finalNormalFrame.isFrameSet())
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"5", "10,10", "4,6", "10,4"})
    void isNotClosedFrame(String shotString) {
        int[] shots = splitInts(shotString);
        Frame finalNormalFrame = NormalFrame.init().last();
        assertThat(finalNormalFrame.isFrameSet())
                .isFalse();

        for (int i = 0; i < shots.length; i++) {
            finalNormalFrame.shot(shots[i]);
        }

        assertThat(finalNormalFrame.isFrameSet())
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
        NormalFrame normalFrame = NormalFrame.init();
        for (int shot : shots) {
            normalFrame.shot(shot);
        }
        assertThat(normalFrame.getFrameScore().getScore())
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
        NormalFrame normalFrame = NormalFrame.init();
        for (int shot : shots) {
            normalFrame.shot(shot);
        }
        assertThat(normalFrame.isScoreCalculated())
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
        NormalFrame normalFrame = NormalFrame.init();
        for (int shot : shots) {
            normalFrame.shot(shot);
        }
        assertThat(normalFrame.isScoreCalculated())
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
        Frame finalNormalFrame = NormalFrame.init().last();
        for (int shot : shots) {
            finalNormalFrame.shot(shot);
        }
        assertThat(finalNormalFrame.isScoreCalculated())
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
        Frame finalNormalFrame = NormalFrame.init().last();
        for (int shot : shots) {
            finalNormalFrame.shot(shot);
        }
        assertThat(finalNormalFrame.isScoreCalculated())
                .isFalse();
    }

}
