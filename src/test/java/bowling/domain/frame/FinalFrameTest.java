package bowling.domain.frame;

import bowling.domain.score.FinalScores;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {

    @DisplayName("FinalFrame 생성 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"3", "0", "22"})
    void of(int frameNumber) {
        FinalFrame finalFrame = FinalFrame.of(frameNumber, FinalScores.init());

        assertThat(finalFrame.getNumber()).isEqualTo(frameNumber);
        assertThat(finalFrame.canBowl()).isTrue();
    }

    @DisplayName("초구에서 스트라이크 후, 투구가 가능한지 확인 테스트")
    @Test
    void canBowl_firstBowl_strike_test() {
        FinalFrame finalFrame = FinalFrame.of(Frame.LAST_FRAME, FinalScores.init());
        finalFrame.bowl(ScoreTest.MAX_SCORE);

        boolean actual = finalFrame.canBowl();

        assertThat(actual).isTrue();
    }

    @DisplayName("2구가 스페어거나 스트라이크인 경우 다음 투구가 가능한지 확인 테스트")
    @ParameterizedTest
    @CsvSource(value = {"0:10:true", "1:9:true", "3:3:false", "5:5:true"}, delimiter = ':')
    void canBowl_spare_test(String firstValue, String secondValue, boolean expectedResult) {
        FinalFrame finalFrame = FinalFrame.of(Frame.LAST_FRAME, FinalScores.init());
        finalFrame.bowl(Score.of(firstValue));
        finalFrame.bowl(Score.of(secondValue));

        boolean actual = finalFrame.canBowl();

        assertThat(actual).isEqualTo(expectedResult);
    }

    @DisplayName("next 프레임 생성 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"3", "10"})
    void next(int frameNumber) {
        FinalFrame finalFrame = FinalFrame.of(frameNumber, FinalScores.init());

        Frame actualFrame = finalFrame.next();

        assertThat(actualFrame).isInstanceOf(FinalFrame.class);
        assertThat(actualFrame.frameNumber).isEqualTo(frameNumber + 1);
    }

    @DisplayName("Frame Score(점수) 반환 테스트 (게임 전체 결과 합산)")
    @ParameterizedTest
    @MethodSource("makeScoreData")
    void getScore(Score firstScore, Score secondScore, Score bonusScore, Score expectedResult) {
        Frame frame = FinalFrame.of(Frame.LAST_FRAME, FinalScores.init());
        frame.bowl(firstScore);
        frame.bowl(secondScore);
        frame.bowl(bonusScore);

        Score actualResult = frame.getTotalScore();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> makeScoreData() {
        return Stream.of(
                Arguments.of(Score.of("3"), Score.of("7"), Score.of("7"), Score.of("17")),
                Arguments.of(Score.of("0"), Score.of("3"), null, Score.of("3")),
                Arguments.of(Score.of("10"), Score.of("5"), null, Score.of("15"))
        );
    }

}
