package step2.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @Test
    @DisplayName("Score 객체 초기화")
    void initScore() {
        assertThat(Score.of(0, 2)).isInstanceOf(Score.class);
    }

    @Test
    @DisplayName("투구 시 기회 줄이기")
    void bowl() {
        Score score = Score.of(0, 2);
        score.bowl(6);

        Score newScore = Score.of(6, 1);

        assertThat(score).isEqualTo(newScore);
    }

    @Test
    @DisplayName("스트라이크 투구")
    void ofStrike() {
        assertThat(Score.ofStrike()).isEqualTo(Score.of(10, 2));
    }

    @Test
    @DisplayName("스페어 투구")
    void ofSpare() {
        assertThat(Score.ofSpare()).isEqualTo(Score.of(10, 1));
    }

    @Test
    @DisplayName("미스 투구")
    void ofMiss() {
        assertThat(Score.ofMiss(9)).isEqualTo(Score.of(9, 0));
    }

    @Test
    @DisplayName("점수 반환")
    void getScore() {
        Score score = Score.ofMiss(9);
        assertThat(score.getScore()).isEqualTo(9);
    }

    @Test
    @DisplayName("기회가 남았을 때, 점수 가져오기 예외 처리")
    void getScoreException() {
        Score score = Score.ofStrike();
        assertThrows(IllegalArgumentException.class,
                score::getScore);
    }

}