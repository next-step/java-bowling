package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.exception.CannotCalculateException;

class ScoreTest {

    @DisplayName("공을 굴린뒤 점수와 남은 횟수를 확인해본다.")
    @Test
    void bowl() {
        Score score = new Score(10, 2);
        Score bowl = score.bowl(5);
        assertThat(bowl).isEqualTo(new Score(15, 1));
    }

    @DisplayName("점수를 계산 할 수 있는 상태인지 확인한다.")
    @Test
    void canCalucateScore() {
        Score score = new Score(10, 0);
        boolean done = score.canCalculateScore();
        assertThat(done).isTrue();
    }

    @DisplayName("점수를 가져오는데 아직 남은 시도가 있다면 예외를 발생시킨다.")
    @Test
    void getScoreException() {
        Score score = new Score(10, 2);
        assertThatThrownBy(() -> {
            score.getScore();
        }).isInstanceOf(CannotCalculateException.class);
    }

    @DisplayName("점수를 가져온다")
    @Test
    public void getScore() {
        Score score = new Score(22, 0);
        assertThat(score.getScore()).isEqualTo(22);
    }

    @DisplayName("미스일 경우 점수")
    @Test
    void miss() {
        Score miss = Score.ofMiss(9);
        assertThat(miss).isEqualTo(new Score(9, 0));
    }

    @DisplayName("스페어일 경우 점수")
    @Test
    void spare() {
        Score spare = Score.ofSpare();
        assertThat(spare).isEqualTo(new Score(10, 1));
    }

    @DisplayName("스트라이크일 경우 점수")
    @Test
    void strike() {
        Score strike = Score.ofStrike();
        assertThat(strike).isEqualTo(new Score(10, 2));
    }
}
