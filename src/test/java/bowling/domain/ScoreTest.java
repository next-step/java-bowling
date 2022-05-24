package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    @DisplayName("score 가 10점인 경우 done")
    void score10ShouldBeDone() {
        Score score = new Score(10);
        assertThat(score.done()).isTrue();
    }

    @Test
    @DisplayName("score 10 미만인 경우 done이 아님")
    void scoreLowerThan9ShouldNotDone() {
        Score score = new Score(9);
        assertThat(score.done()).isFalse();
    }

    @Test
    @DisplayName("빈 score는 공백")
    void payloadForEmpty() {
        assertThat(Score.scoreBoard(new Score())).isEqualTo(Score.format(" "));
    }

    @Test
    @DisplayName("strike는 X출력")
    void payloadForStrike() {
        assertThat(Score.scoreBoard(new Score(10))).isEqualTo(Score.format("X"));
    }

    @Test
    @DisplayName("두번째 시도인 경우 점수 출력")
    void payloadForSecond() {
        assertThat(Score.scoreBoard(new Score(2))).isEqualTo(Score.format("2"));
    }

    @Test
    @DisplayName("0 인경우 - 출력")
    void payloadForGutter() {
        assertThat(Score.scoreBoard(new Score(0, 0))).isEqualTo(Score.format("-|-"));
        assertThat(Score.scoreBoard(new Score(1, 0))).isEqualTo(Score.format("1|-"));
        assertThat(Score.scoreBoard(new Score(0, 1))).isEqualTo(Score.format("-|1"));
    }

    @Test
    @DisplayName("Miss 인 경우 first|second 형식으로 출력")
    void payloadForMiss() {
        assertThat(Score.scoreBoard(new Score(1, 2))).isEqualTo(Score.format("1|2"));
    }

    @Test
    @DisplayName("Spare 경우 first|/ 형식으로 출력")
    void payloadForSpare() {
        assertThat(Score.scoreBoard(new Score(1, 9))).isEqualTo(Score.format("1|/"));
    }

    @Test
    @DisplayName("두 번째 스코어도 있어야지만 done")
    void onlyWithSecondScoreShouldBeDone() {
        assertThat(new Score(1).done()).isFalse();
        assertThat(new Score(1, 2).done()).isTrue();
    }
}
