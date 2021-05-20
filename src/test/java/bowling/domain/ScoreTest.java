//package bowling.domain;
//
//import bowling.domain.Player.Score;
//import bowling.exception.CustomException;
//import bowling.exception.ErrorCode;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//class ScoreTest {
//
//    private Score score;
//
//    @BeforeEach
//    void setup() {
//        score = new Score();
//    }
//
//    @ParameterizedTest
//    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
//    @DisplayName("점수를 생성할 수 있다")
//    void canAddScore(int rawScore) {
//        score.addScore(rawScore);
//        assertThat(score.getClass()).isEqualTo(Score.class);
//    }
//
//    @ParameterizedTest
//    @ValueSource(ints = {-1, 11, 0x7fffffff})
//    @DisplayName("범위에 맞지 않는 점수를 더하면 INVALID_SCORE를 던진다")
//    void invalidAdditionThrowsException(int rawScore) {
//        CustomException customException = assertThrows(CustomException.class, () -> score.addScore(rawScore));
//        assertThat(customException.errorCode()).isEqualTo(ErrorCode.INVALID_SCORE);
//    }
//
//    @ParameterizedTest
//    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
//    @DisplayName("범위에 맞지 않는 점수를 더하면 INVALID_SCORE_ADDITION를 던진다")
//    void additionOnClosedScoreThrowsException(int rawScore) {
//        score.endScoring();
//        CustomException customException = assertThrows(CustomException.class, () -> score.addScore(rawScore));
//        assertThat(customException.errorCode()).isEqualTo(ErrorCode.INVALID_SCORE_ADDITION);
//    }
//
//}
