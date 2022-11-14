package bowling.step2.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class ScoreTest {
    @Test
    void 볼링점수가_크기가_잘못되었을_때() {
        assertThatThrownBy(() -> new Score(11))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Score.SCORE_SIZE_EXCEPTION);
    }

    @Test
    void 볼링점수가_알맞은_숫자일_때() {
        Score score = new Score(2);
        assertSoftly(softly -> {
            assertThat(score.score()).isEqualTo(2);
            assertThat(score.isStrike()).isFalse();
            assertThat(score.isZero()).isFalse();
        });
    }


    @Test
    void 볼링점수가_스트라이크일_때() {
        Score score = new Score(10);
        assertSoftly(softly -> {
            assertThat(score.isStrike()).isTrue();
            assertThat(score.isZero()).isFalse();
        });
    }
}