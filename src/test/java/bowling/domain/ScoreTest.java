package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {

    @DisplayName("Miss, Gutter의 경우 바로 값을 계산 할 수 있다")
    @Test
    void calculateScoreWhenMissOrGutterTest() {
        Score missScore = Score.ofMiss(2);
        assertThat(missScore.calculateScore()).isEqualTo(2);
        Score gutterScore = Score.ofGutter(5);
        assertThat(gutterScore.calculateScore()).isEqualTo(5);
    }

    @DisplayName("Spare의 경우 다음 투구의 값도 더한다")
    @Test
    void calculateScoreWhenSpareTest() {
        Score spareScore = Score.ofSpare();
        Score nextScore = Score.ofMiss(9);
        nextScore.spreadScore(spareScore);
        assertThat(spareScore.calculateScore()).isEqualTo(19);
    }

    @DisplayName("Strike의 경우 다음 2 투구의 값도 더한다")
    @Test
    void calculateScoreWhenStrikeTest() {
        Score strikeScore = Score.ofStrike();
        Score nextScore = Score.ofMiss(9);
        nextScore.spreadScore(strikeScore);
        assertThat(strikeScore.calculateScore()).isEqualTo(19);
    }

    @DisplayName("Spare, Strike는 바로 계산 할 수 없다")
    @Test
    void calculateScoreExceptionTest() {
        Score strikeScore = Score.ofStrike();
        Score spareScore = Score.ofSpare();
        assertThatThrownBy(() -> {
            strikeScore.calculateScore();
            spareScore.calculateScore();
        }).isInstanceOf(IllegalStateException.class);
    }
}
