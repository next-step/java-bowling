package bowling.domain.frame;

import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {

    @DisplayName("다음 프레임이 마지막이 아니면 NormalFrame을 생성한다")
    @Test
    void createNext_normal() {
        NormalFrame frame = NormalFrame.createFirst();
        Frame nextFrame = frame.createNext(false);

        assertThat(nextFrame).isInstanceOf(NormalFrame.class);
    }

    @DisplayName("다음 프레임이 마지막이면 FinalFrame을 생성한다")
    @Test
    void createNext_final() {
        NormalFrame frame = NormalFrame.createFirst();
        Frame nextFrame = frame.createNext(true);

        assertThat(nextFrame).isInstanceOf(FinalFrame.class);
    }

    @DisplayName("점수를 더 기록할 수 있는지 확인한다")
    @Test
    void canAddMoreScore() {
        NormalFrame frame = NormalFrame.createFirst();

        boolean canAddMoreScore = frame.canAddMoreScore();

        assertThat(canAddMoreScore).isTrue();
    }

    @DisplayName("총 점수를 구한다")
    @ParameterizedTest
    @MethodSource("calculateTotalScoreArguments")
    void calculateTotalScore(Score totalScore, int expected) {
        assertThat(totalScore).isEqualTo(Score.ofTotal(expected));
    }

    public static Stream<Arguments> calculateTotalScoreArguments() {
        NormalFrame frame1 = NormalFrame.createFirst();
        Frame frame2 = frame1.createNext(false);
        Frame frame3 = frame2.createNext(false);
        Frame frame4 = frame3.createNext(false);

        frame1.addScore(Score.of(10));
        frame2.addScore(Score.of(1));
        frame2.addScore(Score.of(9));
        frame3.addScore(Score.of(10));
        frame4.addScore(Score.of(1));

        return Stream.of(
                Arguments.of(frame1.calculateTotalScore().get(), 20),
                Arguments.of(frame2.calculateTotalScore().get(), 20),
                Arguments.of(frame3.calculateTotalScore().get(), 11));
    }
}
