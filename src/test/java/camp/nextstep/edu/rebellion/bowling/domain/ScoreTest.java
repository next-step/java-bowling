package camp.nextstep.edu.rebellion.bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {
    @DisplayName("넘어뜨린 볼링 핀 수가 잘못 되었을 경우 예외 발생")
    @Test
    public void scoreTest() {
        // given
        int hits = 15;

        // when & then
        assertThatThrownBy(() -> new Score(hits))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("투구 값이 잘못 되었습니다 (볼링 핀 개수 초과) " + hits);
    }

    @DisplayName("Score 가 같은지 확인")
    @Test
    public void equalTest() {
        // given
        Score score = new Score(5);

        // when & then
        assertAll(
                () -> assertThat(score.equals(Score.of(10))).isFalse(),
                () -> assertThat(score.equals(Score.of(5))).isTrue(),
                () -> assertThat(score.getHits() == 5).isTrue()
        );
    }
}
