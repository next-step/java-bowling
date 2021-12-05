package bowling.domain.value;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @Test
    @DisplayName("스트라이크인 경우 다음 2번의 투구까지의 점수 합산 검증")
    void ofStrike() {
        Score score = Score.ofStrike();

        assertThat(score.canCalculateScore()).isFalse();

        score = score.accumulateScore(5);
        score = score.accumulateScore(4);
        assertThat(score.canCalculateScore()).isTrue();

        assertThat(score).isEqualTo(Score.of(19, 0));
    }

    @Test
    @DisplayName("스페어인 경우 다음 1번의 투구까지의 점수 합산 검증")
    void ofSpare() {
        Score score = Score.ofSpare();

        assertThat(score.canCalculateScore()).isFalse();

        score = score.accumulateScore(5);
        assertThat(score.canCalculateScore()).isTrue();

        assertThat(score).isEqualTo(Score.of(15, 0));
    }


    @Test
    @DisplayName("마지막 투구 인 경우 검증")
    void ofMiss() {
        Score score = Score.ofMissOrGutter(8);
        assertThat(score.canCalculateScore()).isTrue();

        assertThat(score).isEqualTo(Score.of(8, 0));
    }
}
