package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import bowling.domain.frame.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreCreatorTest {

    @Test
    @DisplayName("0보다 작은 점수 입력시 Exception")
    void createScoreFailTest() {
        assertThatThrownBy(() -> ScoreCreator.create(Point.inputPoint(-1)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("10보다 큰 점수 입력시 Exception")
    void createScoreFailTest_10보다_큰경우() {
        assertThatThrownBy(() -> ScoreCreator.create(Point.inputPoint(11)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("정상 점수 생성")
    void createScoreTest() {
        Score score = ScoreCreator.create(Point.inputPoint(5));
        assertThat(score.getScore()).isEqualTo("5");
    }

    @Test
    void createScoreStrikeTest() {
        Score score = ScoreCreator.create(Point.inputPoint(10));
        assertThat(score.getScore()).isEqualTo("X");
    }

    @Test
    void createScoreGutterTest() {
        Score score = ScoreCreator.create(Point.inputPoint(0));
        assertThat(score.getScore()).isEqualTo("-");
    }

    @Test
    @DisplayName("첫번째 점수의 합과 두번째 점수의 합이 10점인 경우 Spare")
    void createScoreSpareTest() {
        Score firstScore = ScoreCreator.create(Point.inputPoint(8));
        Score secondScore = firstScore.nextScore(Point.inputPoint(2));
        assertThat(secondScore.getScore()).isEqualTo("/");
    }

    @Test
    @DisplayName("두번재 투구에서도 모든 핀이 쓰러지지 않은 경우 MISS")
    void createScoreMissTest() {
        Score firstScore = ScoreCreator.create(Point.inputPoint(5));
        Score secondScore = firstScore.nextScore(Point.inputPoint(0));
        assertThat(secondScore.getScore()).isEqualTo("-");
    }
}
