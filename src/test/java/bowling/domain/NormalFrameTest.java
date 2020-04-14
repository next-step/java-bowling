package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class NormalFrameTest {

    @Test
    void of() {
        assertThatCode(() -> NormalFrame.init())
                .doesNotThrowAnyException();
    }

    @Test
    void shot() {
        NormalFrame normalFrame = NormalFrame.init();
        normalFrame.shot(4);
        assertThat(normalFrame.getDto().getShotScores())
                .anyMatch(v -> v.getScoreType().equals(ScoreType.MISS))
                .anyMatch(v -> v.getScore() == 4);

        normalFrame.shot(6);
        assertThat(normalFrame.getDto().getShotScores())
                .anyMatch(v -> v.getScoreType().equals(ScoreType.SPARE))
                .anyMatch(v -> v.getScore() == 6);

        assertThatThrownBy(() -> normalFrame.shot(5))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void next() {
        NormalFrame normalFrame = NormalFrame.init();
        assertThatCode(() -> normalFrame.next(5))
                .doesNotThrowAnyException();

        assertThatThrownBy(() -> normalFrame.next(11))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"5,4", "10"})
    void isClosed(String shotString) {
        NormalFrame normalFrame = NormalFrame.init();
        assertThat(normalFrame.isFrameClosed())
                .isFalse();

        int[] shots = splitInts(shotString);
        for (int shot : shots) {
            normalFrame.shot(shot);
        }

        assertThat(normalFrame.isFrameClosed())
                .isTrue();
    }

    private int[] splitInts(String shotString) {
        return Arrays.stream(shotString.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

}