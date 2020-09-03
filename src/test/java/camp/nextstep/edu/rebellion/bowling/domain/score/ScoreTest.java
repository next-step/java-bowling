package camp.nextstep.edu.rebellion.bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {
    @DisplayName("점수가 잘 생성 되는지 확인")
    @Test
    public void scoreTest() {
        // given
        int hits = 5;

        // when
        Score score = Score.of(5);

        // then
        assertAll(
                () -> assertThat(score.equals(hits)).isTrue(),
                () -> assertThat(score.equals(Score.of(5))).isTrue(),
                () -> assertThat(score.getHits()).isEqualTo(hits)
        );
    }

    @DisplayName("점수 값 범위가 잘못된 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    public void scoreThrownTest(int hits) {
        // when & then
        assertThatThrownBy(() -> Score.of(hits))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("투구 값이 잘못 되었습니다 (볼링 핀 개수 초과) " + hits);
    }
}
