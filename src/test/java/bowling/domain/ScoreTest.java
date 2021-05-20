package bowling.domain;

import bowling.domain.Score;
import bowling.domain.TestUtil;
import bowling.exception.CustomException;
import bowling.exception.ErrorCode;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScoreTest {

    private Score score;

    @BeforeEach
    void setup() {
        score = new Score();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 5, 10})
    @DisplayName("점수를 더할 수 있다")
    void canAddScore(int rawScore) {
        score.addScore(rawScore);
        assertThat(score.getClass()).isEqualTo(Score.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11, 0x7fffffff})
    @DisplayName("범위에 맞지 않는 수를 더하면 INVALID_SCORE를 던진다")
    void invalidAdditionThrowsException(int rawScore) {
        CustomException customException = assertThrows(CustomException.class, () -> score.addScore(rawScore));
        assertThat(customException.errorCode()).isEqualTo(ErrorCode.INVALID_SCORE);
    }

    @Test
    @DisplayName("점수반영이 끝난 이후 다시 점수를 더하면 INVALID_SCORE_ADDITION를 던진다")
    void additionOnClosedScoreThrowsException() {
        int rawScore = 0;
        score.addScore(rawScore);
        score.addScore(rawScore);
        CustomException customException = assertThrows(CustomException.class, () -> score.addScore(rawScore));
        assertThat(customException.errorCode()).isEqualTo(ErrorCode.INVALID_SCORE_ADDITION);
    }

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource(value = "canAddValidBonusScoresParameters")
    @DisplayName("보너스가 있을 경우 보너스의 횟수만큼 점수를 더할 수 있다")
    void canAddValidBonusScores(String scoreString, String caseName) {
        SoftAssertions softAssertions = new SoftAssertions();
        score.addBonus();
        List<Integer> rawScores = TestUtil.stringListToIntegerList(scoreString, ",");
        for (int rawScore : rawScores) {
            softAssertions.assertThat(score.hasBonus()).isTrue();
            score.addScore(rawScore);
        }
        softAssertions.assertThat(score.hasBonus()).isFalse();
    }

    static Stream<Arguments> canAddValidBonusScoresParameters() {
        return Stream.of(
                Arguments.of("0,0", "Strike"),
                Arguments.of("0", "Spare")
        );
    }

}
