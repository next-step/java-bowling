package bowling.domain.frame;

import bowling.exception.ExceptionMessage;
import bowling.domain.score.NormalScores;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class NormalFrameTest {

    @DisplayName("NormalFrame 생성 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1", "70", "3"})
    void of(int frameNumber) {
        NormalFrame normalFrame = NormalFrame.of(frameNumber, NormalScores.init());

        assertThat(normalFrame.getNumber()).isEqualTo(frameNumber);
        assertThat(normalFrame.canBowl()).isTrue();
    }

    @DisplayName("NormalFrame 1프레임 생성 테스트")
    @Test
    void first() {
        NormalFrame normalFrame = NormalFrame.first();

        assertThat(normalFrame.getNumber()).isEqualTo(Frame.FIRST_FRAME);
        assertThat(normalFrame.canBowl()).isTrue();
    }

    @DisplayName("NormalFrame 투구 예외 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"-20", "-1"})
    void bowl_invalid_value(String firstValue) {
        NormalFrame normalFrame = NormalFrame.first();
        String exceptionMessage = ExceptionMessage.INVALID_SCORE_VALUE;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> normalFrame.bowl(Score.of(firstValue)))
                .withMessage(exceptionMessage);
    }

    @DisplayName("초구 후, 2구가 가능한지 확인 테스트")
    @ParameterizedTest
    @CsvSource(value = {"3:true", "10:false", "9:true"}, delimiter = ':')
    void canBowl_second_bowl(String firstValue, boolean expectedResult) {
        NormalFrame normalFrame = NormalFrame.first();
        normalFrame.bowl(Score.of(firstValue));

        boolean actual = normalFrame.canBowl();

        assertThat(actual).isEqualTo(expectedResult);
    }

    @DisplayName("2구까지 진행 후, 다음 투구가 가능한지 확인 테스트")
    @ParameterizedTest
    @CsvSource(value = {"3:3", "1:9", "0:0"}, delimiter = ':')
    void canBowl_second_bowl_next_bowl(String firstValue, String secondValue) {
        NormalFrame normalFrame = NormalFrame.first();
        normalFrame.bowl(Score.of(firstValue));
        normalFrame.bowl(Score.of(secondValue));

        boolean actual = normalFrame.canBowl();

        assertThat(actual).isFalse();
    }

    @DisplayName("next 프레임 생성 테스트")
    @ParameterizedTest
    @MethodSource("makeNextData")
    void next(int frameNumber, Class<?> type) {
        NormalFrame normalFrame = NormalFrame.of(frameNumber, NormalScores.init());

        Frame actualFrame = normalFrame.next();

        assertThat(actualFrame).isInstanceOf(type);
        assertThat(actualFrame.frameNumber).isEqualTo(frameNumber + 1);
    }

    private static Stream<Arguments> makeNextData() {
        return Stream.of(
                Arguments.of(3, NormalFrame.class),
                Arguments.of(9, FinalFrame.class),
                Arguments.of(11, FinalFrame.class)
        );
    }

    @DisplayName("Frame Score(점수) 반환 테스트 - 추가 합산 점수 없는 경우")
    @ParameterizedTest
    @MethodSource("makeScoreNoBonusData")
    void getScore_no_bonus_score(Score firstScore, Score secondScore, Score thirdScore, int expectedResult) {
        Frame frame = NormalFrame.of(Frame.FIRST_FRAME, NormalScores.init());
        frame.bowl(firstScore);
        frame.bowl(secondScore);

        Frame nextFrame = frame.next();
        nextFrame.bowl(thirdScore);

        int actualResult = frame.getScore();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> makeScoreNoBonusData() {
        return Stream.of(
                Arguments.of(Score.of("2"), Score.of("3"), Score.of("10"), 5),
                Arguments.of(Score.of("4"), Score.of("3"), Score.of("7"), 8)
        );
    }

    @DisplayName("Frame Score(점수) 반환 테스트 - 추가 합산 점수 있는 경우 (스트라이크)")
    @ParameterizedTest
    @MethodSource("makeScoreStrikeData")
    void getScore_strike(Score firstScore, Score secondScore, Score thirdScore, int expectedResult) {
        Frame frame = NormalFrame.of(Frame.FIRST_FRAME, NormalScores.init());
        frame.bowl(firstScore);

        Frame nextFrame = frame.next();
        nextFrame.bowl(secondScore);
        nextFrame.bowl(thirdScore);

        int actualResult = frame.getScore();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> makeScoreStrikeData() {
        return Stream.of(
                Arguments.of(Score.of("10"), Score.of("3"), Score.of("6"), 19),
                Arguments.of(Score.of("10"), Score.of("1"), Score.of("1"), 12)
        );
    }

    @DisplayName("Frame Score(점수) 반환 테스트 - 추가 합산 점수 있는 경우 (스페어)")
    @ParameterizedTest
    @MethodSource("makeScoreSpareData")
    void getScore_spare(Score firstScore, Score secondScore, Score thirdScore, Score fourthScore, int expectedResult) {
        Frame frame = NormalFrame.of(Frame.FIRST_FRAME, NormalScores.init());
        frame.bowl(firstScore);
        frame.bowl(secondScore);

        Frame nextFrame = frame.next();
        nextFrame.bowl(thirdScore);

        if(fourthScore != null) {
            nextFrame.bowl(fourthScore);
        }

        int actualResult = frame.getScore();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> makeScoreSpareData() {
        return Stream.of(
                Arguments.of(Score.of("2"), Score.of("8"), Score.of("6"), Score.of("3"), 16),
                Arguments.of(Score.of("6"), Score.of("4"), Score.of("7"), null, 17)
        );
    }
}
