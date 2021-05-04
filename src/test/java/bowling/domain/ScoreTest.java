package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    void 점수를생성() {
        Score score = Score.of(5);
        assertThat(score).isEqualTo(Score.of(5));
    }

    @Test
    void 추가점수합산() {
        Score score = Score.of(5).add(6);
        assertThat(score).isEqualTo(Score.of(11, 0));
    }

    @Test
    void 값비교(){
        assertThat(Score.of(5).compareTo(5)).isEqualTo(0);
        assertThat(Score.of(6).compareTo(5)).isEqualTo(1);
        assertThat(Score.of(4).compareTo(5)).isEqualTo(-1);
    }
}
