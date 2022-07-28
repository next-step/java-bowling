package bowling2.domain.score;

import bowling2.domain.frame.FinalFrame;
import bowling2.domain.frame.NormalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    }



    @DisplayName("next는 스트라이크가 아닌 경우 점수 계산 성공")
    @Test
    void compute_success2() {
    }
}
