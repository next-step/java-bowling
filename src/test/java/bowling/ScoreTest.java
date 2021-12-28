package bowling;

import bowling.domain.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    void 스트라이크생성과_추가투구0번_점수계산() {
        Score score = Score.ofStrike();
        assertThat(score.canCalculateScore()).isFalse();
    }

    @Test
    void 스트라이크생성과_추가투구1번_점수계산() {
        Score score = Score.ofStrike();
        score = score.bowl(10);

        assertThat(score.canCalculateScore()).isFalse();
    }

    @Test
    void 스트라이크생성과_추가투구2번_점수계산() {
        Score score = Score.ofStrike();
        score = score.bowl(10);
        score = score.bowl(10);

        assertThat(score.getScore()).isEqualTo(30);
    }

    @Test
    void 스페어생성과_추가투구0번_점수계산() {
        Score score = Score.ofSpare();
        assertThat(score.canCalculateScore()).isFalse();
    }

    @Test
    void 스페어생성과_추가투구1번_점수계산() {
        Score score = Score.ofSpare();
        score = score.bowl(10);

        assertThat(score.getScore()).isEqualTo(20);
    }

    @Test
    void 미스생성과_점수계산() {
        Score score = Score.ofMiss(4, 5);
        assertThat(score.getScore()).isEqualTo(9);
    }

    @Test
    void 퍼스트볼생성과_점수계산() {
        Score score = Score.ofFirst(5);
        assertThat(score.getScore()).isEqualTo(5);
    }

}
