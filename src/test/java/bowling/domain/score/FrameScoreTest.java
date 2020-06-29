package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameScoreTest {

    private static final Score ZERO = Score.of(0);
    private static final Score FIVE = Score.of(5);
    private static final Score TEN = Score.of(10);

    @DisplayName("첫번째와 두번째 투구의 점수의 합이 10을 넘으면 IllegalArgumentException")
    @Test
    void inputSecondScore_over10() {
        FrameScore frameScore = FrameScore.create();
        frameScore.add(FIVE);

        assertThatThrownBy(() -> frameScore.add(TEN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("점수의 합");
    }

    @DisplayName("두번째 투구의 점수를 입력하지 않은채 결과를 확인하면 IllegalStateException")
    @Test
    void checkResult_withoutSecondScore() {
        FrameScore frameScore = FrameScore.create();
        frameScore.add(FIVE);

        assertThatThrownBy(frameScore::checkResult)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("두번째 투구의 점수");
    }

    @DisplayName("첫번째, 두번째 투구의 점수를 모두 입력하면 결과를 확인할 수 있다")
    @Test
    void checkResult_inputSecondScore() {
        FrameScore frameScore = FrameScore.create();
        frameScore.add(FIVE);
        frameScore.add(ZERO);

        Result result = frameScore.checkResult();

        assertThat(result).isNotNull();
    }

    @DisplayName("첫번째 투구의 점수가 10이면 두번째 투구의 점수를 입력하지 않아도 결과를 확인할 수 있다")
    @Test
    void first10_inputSecondScore() {
        FrameScore frameScore = FrameScore.create();
        frameScore.add(TEN);

        Result result = frameScore.checkResult();

        assertThat(result).isNotNull();
    }

    @DisplayName("결과를 확인한다")
    @ParameterizedTest
    @MethodSource("checkResultArguments")
    void checkResult(Result result, Result expected) {
        assertThat(result).isEqualTo(expected);
    }

    public static Stream<Arguments> checkResultArguments() {
        FrameScore frameScore1 = FrameScore.create();
        frameScore1.add(TEN);
        Result result1 = frameScore1.checkResult();

        FrameScore frameScore2 = FrameScore.create();
        frameScore2.add(FIVE);
        frameScore2.add(FIVE);
        Result result2 = frameScore2.checkResult();

        FrameScore frameScore3 = FrameScore.create();
        frameScore3.add(FIVE);
        frameScore3.add(ZERO);
        Result result3 = frameScore3.checkResult();

        FrameScore frameScore4 = FrameScore.create();
        frameScore4.add(ZERO);
        frameScore4.add(ZERO);
        Result result4 = frameScore4.checkResult();

        return Stream.of(
                Arguments.of(result1, Result.STRIKE),
                Arguments.of(result2, Result.SPARE),
                Arguments.of(result3, Result.MISS),
                Arguments.of(result4, Result.GUTTER));
    }
}
