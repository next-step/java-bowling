package bowling.domain.frame;

import bowling.domain.frame.score.DefaultFrameScore;
import bowling.domain.frame.score.FrameScore;
import bowling.domain.shot.type.ShotType;
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

        assertThat(normalFrame.shots())
                .anyMatch(v -> ShotType.MISS_FIRST.equals(v.scoreType()) && v.singleScore() == 4)
                .anyMatch(v -> ShotType.SPARE.equals(v.scoreType()) && v.singleScore() == 6);

        assertThat(normalFrame.shots())
                .hasSize(2);

        assertThatThrownBy(() -> normalFrame.shot(5))
                .isInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"5,4", "10"})
    void isFrameSet(String shotString) {
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
            "4,4:8:true",
            "1,9:10:false",
            "10:10:false"
    }, delimiter = ':')
    void getFrameScore(String shotString, int expectScore, boolean expectCalculated) {
        int[] shots = splitInts(shotString);
        NormalFrame normalFrame = NormalFrame.init();
        for (int shot : shots) {
            normalFrame.shot(shot);
        }
        assertThat(normalFrame.getFrameScore().isCalculated())
                .isEqualTo(expectCalculated);
        if (expectCalculated) {
            assertThat(normalFrame.getFrameScore().getScore())
                    .isEqualTo(expectScore);
        }
    }

    @Test
    void getFrameNumber() {
        Frame normalFrame = NormalFrame.init();
        for (int i = 1; i < 10; i++) {
            assertThat(normalFrame.getFrameNumber())
                    .isEqualTo(i);
            normalFrame = normalFrame.next();
        }
    }

    private int[] splitInts(String shotString) {
        return Arrays.stream(shotString.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    @Test
    void getShotCount() {
        NormalFrame normalFrame = NormalFrame.init();
        assertThat(normalFrame.getShotsCount())
                .isEqualTo(0);
        normalFrame.shot(1);
        assertThat(normalFrame.getShotsCount())
                .isEqualTo(1);
    }

    @Test
    void addBonus() {
        FrameScore frameScore = DefaultFrameScore.of(10, 1);
        NormalFrame normalFrame = NormalFrame.init();
        assertThat(normalFrame.addBonus(frameScore).isCalculated())
                .isFalse();

        normalFrame.shot(5);
        assertThat(normalFrame.addBonus(frameScore))
                .matches(FrameScore::isCalculated)
                .matches(v -> v.getScore() == 15);
    }
}
