package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {

    @Test
    @DisplayName("0보다 작은 점수 입력시 Exception")
    void negativeNumberTest() {
        assertThatThrownBy(() -> ScoreFactory.generateScore(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("10보다 큰 점수 입력시 Exception")
    void biggerThanTenTest() {
        assertThatThrownBy(() -> ScoreFactory.generateScore(11))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("providePoint")
    @DisplayName("정상 점수 생성")
    void createScoreTest(int point) {
        Score score = ScoreFactory.generateScore(point);
        assertThat(score.getPoint()).isEqualTo(point);
    }

    private static final Stream<Arguments> providePoint() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(2),
                Arguments.of(3),
                Arguments.of(4),
                Arguments.of(5),
                Arguments.of(6),
                Arguments.of(7),
                Arguments.of(8),
                Arguments.of(9),
                Arguments.of(10)
        );
    }

    @Test
    @DisplayName("첫번째 점수가 10점인 경우 STRIKE")
    void strikeTest() {
        Score score = ScoreFactory.generateScore(10);
        assertThat(score.getPoint()).isEqualTo(10);
        assertThat(score.getScoreType()).isEqualTo(ScoreType.STRIKE);
    }

    @Test
    @DisplayName("하나도 맞추지 못했을 경우 GUTTER")
    void gutterTest() {
        Score score = ScoreFactory.generateScore(0);
        assertThat(score.getScoreType()).isEqualTo(ScoreType.GUTTER);
    }

    @Test
    @DisplayName("첫번째 점수의 합과 두번째 점수의 합이 10점인 경우 Spare")
    void spareTest() {
        Score firstScore = ScoreFactory.generateScore(5);
        Score secondScore = firstScore.nextScore(5);
        assertThat(secondScore.getPoint()).isEqualTo(5);
        assertThat(secondScore.getScoreType()).isEqualTo(ScoreType.SPARE);
    }

    @Test
    @DisplayName("두번재 투구에서도 모든 핀이 쓰러지지 않은 경우 MISS")
    void missTest() {
        Score firstScore = ScoreFactory.generateScore(5);
        Score secondScore = firstScore.nextScore(3);
        assertThat(secondScore.getScoreType()).isEqualTo(ScoreType.MISS);
    }

}