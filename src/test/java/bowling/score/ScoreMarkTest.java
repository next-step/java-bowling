package bowling.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreMarkTest {

    @Test
    @DisplayName("스트라이크 기호 확인")
    void state() {
        String mark = ScoreMark.STRIKE.toString();
        assertThat(mark).isEqualTo("X");
    }

    @Test
    @DisplayName("스페어 기호 확인")
    void spare() {
        String mark = ScoreMark.SPARE.toString();
        assertThat(mark).isEqualTo("/");
    }

    @Test
    @DisplayName("거터 기호 확인")
    void gutter() {
        String mark = ScoreMark.GUTTER.toString();
        assertThat(mark).isEqualTo("-");
    }

}
