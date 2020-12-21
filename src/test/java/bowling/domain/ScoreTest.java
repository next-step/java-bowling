package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @Test
    @DisplayName("스트라이크 점수 가져오기")
    void getStrikeScore() {
        assertThat(Score.getStrikeScore()).isEqualTo(new Score(10, 2));
    }

    @Test
    @DisplayName("스페어 점수 가져오기")
    void getSpareScore() {
        assertThat(Score.getSpareScore()).isEqualTo(new Score(10, 1));
    }

    @Test
    @DisplayName("미스 점수 가져오기")
    void getMissScore() {
        assertThat(Score.getMissScore(1)).isEqualTo(new Score(1, 0));
    }

    @Test
    @DisplayName("공 던지기 시 점수 더하기")
    void pitch() {
        Score score = new Score();

        assertThat(score.pitch(3)).isEqualTo(new Score(3, 1));
    }

    @Test
    void isFinished() {
        assertThat(new Score(0, 0).isFinished()).isTrue();
    }
}