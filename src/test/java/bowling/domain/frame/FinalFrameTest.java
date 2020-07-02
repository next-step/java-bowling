package bowling.domain.frame;

import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {

    @DisplayName("스트라이크면 점수를 더 기록할 수 있다")
    @ParameterizedTest
    @MethodSource("canAddMoreScore_spareArguments")
    void canAddMoreScore_spare(boolean canAddMoreScore, boolean expected) {
        assertThat(canAddMoreScore).isEqualTo(expected);
    }

    public static Stream<Arguments> canAddMoreScore_spareArguments() {
        Frame frame = FinalFrame.create(1);
        frame.addScore(Score.of(1));
        boolean canAddMoreScore1 = frame.canAddMoreScore();

        frame.addScore(Score.of(9));
        boolean canAddMoreScore2 = frame.canAddMoreScore();

        frame.addScore(Score.of(1));
        boolean canAddMoreScore3 = frame.canAddMoreScore();

        return Stream.of(
                Arguments.of(canAddMoreScore1, true),
                Arguments.of(canAddMoreScore2, true),
                Arguments.of(canAddMoreScore3, false));
    }

    @DisplayName("스트라이크면 점수를 더 기록할 수 있다")
    @ParameterizedTest
    @MethodSource("canAddMoreScore_strikeArguments")
    void canAddMoreScore_strike(boolean canAddMoreScore, boolean expected) {
        assertThat(canAddMoreScore).isEqualTo(expected);
    }

    public static Stream<Arguments> canAddMoreScore_strikeArguments() {
        Frame frame = FinalFrame.create(1);
        frame.addScore(Score.of(10));
        boolean canAddMoreScore1 = frame.canAddMoreScore();

        frame.addScore(Score.of(1));
        boolean canAddMoreScore2 = frame.canAddMoreScore();

        return Stream.of(
                Arguments.of(canAddMoreScore1, true),
                Arguments.of(canAddMoreScore2, false));
    }

    @DisplayName("총 점수를 구한다")
    @Test
    void calculateTotalScore() {
        Frame frame = FinalFrame.create(1);
        frame.addScore(Score.of(10));
        frame.addScore(Score.of(3));

        Score totalScore = frame.calculateTotalScore().get();

        assertThat(totalScore).isEqualTo(Score.ofTotal(13));
    }
}
