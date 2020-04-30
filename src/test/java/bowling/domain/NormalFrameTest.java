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
            "0,0:0"
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
    @CsvSource(value = {
            "10:4,3:true",
            "1,9:2:true",
            "10:5:false",
            "1,9::false"
    }, delimiter = ':')
    void isScoreCalculated(String shotString, String bonusesString, boolean expectCalculated) {
        int[] shots = splitInts(shotString);
        int[] bonuses = bonusesString == null ? new int[0] : splitInts(bonusesString);
        NormalFrame normalFrame = NormalFrame.init();
        for (int shot : shots) {
            normalFrame.shot(shot);
        }
        for (int bonus : bonuses) {
            normalFrame.getFrameScore().addBonus(bonus);
        }
        assertThat(normalFrame.getFrameScore().isCalculated())
                .isEqualTo(expectCalculated);
    }

    private int[] splitInts(String shotString) {
        return Arrays.stream(shotString.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
