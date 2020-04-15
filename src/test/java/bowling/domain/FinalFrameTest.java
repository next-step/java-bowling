package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {
    @Test
    void of() {
        assertThatCode(() -> FinalFrame.of())
                .doesNotThrowAnyException();
    }

    @Test
    void shot() {
        Frame finalFrame = FinalFrame.of();
        finalFrame.shot(4);
        assertThat(finalFrame.getDto().getShotScores())
                .anyMatch(v -> v.getScoreType().equals(ScoreType.MISS))
                .anyMatch(v -> v.getScore() == 4);

        finalFrame.shot(6);
        assertThat(finalFrame.getDto().getShotScores())
                .anyMatch(v -> v.getScoreType().equals(ScoreType.SPARE))
                .anyMatch(v -> v.getScore() == 6);

        finalFrame.shot(5);

        assertThatThrownBy(() -> finalFrame.shot(5))
                .isInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"5,4", "10,10,10","4,6,4","10,4,5"})
    void isClosed(String shotString) {
        Frame finalFrame = FinalFrame.of();
        assertThat(finalFrame.isFrameClosed())
                .isFalse();

        int[] shots = splitInts(shotString);
        for (int shot : shots) {
            finalFrame.shot(shot);
        }

        assertThat(finalFrame.isFrameClosed())
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"5", "10,10","4,6","10,4"})
    void isNotClosed(String shotString) {
        Frame finalFrame = FinalFrame.of();
        assertThat(finalFrame.isFrameClosed())
                .isFalse();

        int[] shots = splitInts(shotString);
        for (int shot : shots) {
            finalFrame.shot(shot);
        }

        assertThat(finalFrame.isFrameClosed())
                .isFalse();
    }

    private int[] splitInts(String shotString) {
        return Arrays.stream(shotString.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
