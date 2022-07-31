package bowling2.domain.score;

import bowling2.domain.frame.FinalFrame;
import bowling2.domain.frame.NormalFrame;
import bowling2.exception.BowlingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StrikeScoreStrategyTest {

    private StrikeScoreStrategy strikeScoreStrategy;

    @BeforeEach
    void setUp() {
        strikeScoreStrategy = new StrikeScoreStrategy();
    }

    @DisplayName("9번째 인덱스일 때 다음 프레임이 스트라이크 2개 있는 경우 점수 계산 성공")
    @Test
    void compute_success() {
        NormalFrame prev = new NormalFrame(8, 0, null, null, null, 70);
        FinalFrame next = new FinalFrame(10, 0, List.of(10, 10), null, null);
        NormalFrame current = new NormalFrame(9, 0, List.of(10), prev, next, 0);

        assertThat(strikeScoreStrategy.compute(current)).isEqualTo(100);
    }

    @DisplayName("next가 스트라이크라면 그 다음 next도 이용해 점수계산 성공")
    @Test
    void compute_success3() {
        // given
        NormalFrame prev = new NormalFrame(3, 0, null, null, null, 70);
        NormalFrame nextnext = new NormalFrame(6, 0, List.of(10), null, null, 0);
        NormalFrame next = new NormalFrame(5, 0, List.of(10), null, nextnext, 0);

        NormalFrame current = new NormalFrame(4, 0, List.of(10), prev, next, 0);

        // when, then
        assertThat(strikeScoreStrategy.compute(current)).isEqualTo(100);
    }

    @DisplayName("next는 스트라이크가 아닌 경우 점수 계산 성공")
    @Test
    void compute_success2() {
        // given
        NormalFrame prev = new NormalFrame(3, 0, null, null, null, 80);
        NormalFrame next = new NormalFrame(5, 0, List.of(5, 5), null, null, 0);

        NormalFrame current = new NormalFrame(4, 0, List.of(10), prev, next, 0);

        // when, then
        assertThat(strikeScoreStrategy.compute(current)).isEqualTo(100);
    }

    @DisplayName("nextFrame이 null인 경우")
    @Test
    void compute_fail() {
        NormalFrame prev = new NormalFrame(3, 0, List.of(3, 3), null, null, 30);
        NormalFrame next = null;
        NormalFrame current = new NormalFrame(4, 0, List.of(3, 7), prev, next, 0);

        assertThatThrownBy(() -> strikeScoreStrategy.compute(current))
                .isInstanceOf(BowlingException.class);
    }
}
