package bowling.step2.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ScoreTest {
    @Test
    void 볼링점수가_잘못입력_되었을_때() {
        assertThatThrownBy(()-> new Score("D"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Score.SCORE_EXCEPTION);

        assertThatThrownBy(()-> new Score("13"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Score.SCORE_EXCEPTION);
    }

    @Test
    void 볼링점수가_스트라이크일_때() {
        Score score = new Score("X");
        assertThat(score.score()).isEqualTo("X");
        assertThat(score.isStrike()).isTrue();
    }

    @Test
    void 볼링점수가_스페어일_때() {
        Score score = new Score("/");
        assertThat(score.score()).isEqualTo("/");
        assertThat(score.isSpare()).isTrue();
    }

    @Test
    void 볼링점수가_숫자일_때() {
        Score score = new Score("2");
        assertThat(score.score()).isEqualTo("2");
        assertThat(score.isSpare()).isFalse();
    }
}