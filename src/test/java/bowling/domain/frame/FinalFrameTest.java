package bowling.domain.frame;

import bowling.domain.frame.score.DefaultFrameScore;
import bowling.domain.frame.score.FrameScore;
import bowling.domain.shot.type.ShotType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalFrameTest {
    private FinalFrame finalFrame;

    @BeforeEach
    void setFinal() {
        finalFrame = FinalFrame.of();
    }

    @Test
    void next() {
        assertThatThrownBy(finalFrame::next)
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"5,4", "10,10,10", "4,6,4", "10,4,5"})
    void isFrameSet(String shotString) {
        int[] shots = splitInts(shotString);
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
    void isFrameSetFalse(String shotString) {
        int[] shots = splitInts(shotString);

        assertThat(finalFrame.isFrameSet())
                .isFalse();

        for (int shot : shots) {
            finalFrame.shot(shot);
        }

        assertThat(finalFrame.isFrameSet())
                .isFalse();
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
        FinalFrame finalFrame = FinalFrame.of();
        for (int shot : shots) {
            finalFrame.shot(shot);
        }
        assertThat(finalFrame.getFrameScore().getScore())
                .isEqualTo(expectScore);
    }

    @Test
    void shot() {

        finalFrame.shot(4);
        assertThat(finalFrame.shots().stream())
                .anyMatch(v -> ShotType.MISS_FIRST.equals(v.scoreType()))
                .anyMatch(v -> v.singleScore() == 4);

        finalFrame.shot(6);
        assertThat(finalFrame.shots().stream())
                .anyMatch(v -> ShotType.SPARE.equals(v.scoreType()))
                .anyMatch(v -> v.singleScore() == 6);

        finalFrame.shot(5);
        assertThat(finalFrame.getFrameScore().getScore())
                .isEqualTo(15);

        assertThatThrownBy(() -> finalFrame.shot(5))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void getFrameNumber() {
        assertThat(FinalFrame.of().getFrameNumber())
                .isEqualTo(10);
    }

    private int[] splitInts(String shotString) {
        return Arrays.stream(shotString.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    @Test
    void getShotCount() {
        FinalFrame finalFrame = FinalFrame.of();
        assertThat(finalFrame.getShotsCount())
                .isEqualTo(0);
        finalFrame.shot(1);
        assertThat(finalFrame.getShotsCount())
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
