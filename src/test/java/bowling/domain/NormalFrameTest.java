package bowling.domain;

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

    private int[] splitInts(String shotString) {
        return Arrays.stream(shotString.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
