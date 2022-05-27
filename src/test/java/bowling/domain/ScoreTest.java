package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    void createTest() {
        Score score = new Score();

        assertThat(score).isNotNull();
    }

    @DisplayName("넘어뜨린 점수가 맞는지 확인해서 맞는 경우")
    @Test
    void isHitTest() {
        final int hitScore = 8;

        Score score = new Score(hitScore);

        assertThat(score.isHit(hitScore)).isTrue();
    }

    @DisplayName("넘어뜨린 점수가 맞는지 확인해서 틀린 경우")
    @Test
    void isHitTest2() {
        final int hitScore = 8;

        Score score = new Score(hitScore);

        assertThat(score.isHit(hitScore-1)).isFalse();
    }

}
