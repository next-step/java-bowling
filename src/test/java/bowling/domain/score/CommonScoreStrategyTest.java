package bowling.domain.score;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CommonScoreStrategyTest {

    private CommonScoreStrategy commonScoreStrategy;

    @BeforeEach
    void setUp() {
        commonScoreStrategy = new CommonScoreStrategy();
    }

    @DisplayName("노말 프레임 점수 계산 성공")
    @Test
    void compute_normalFrame_success() {
        NormalFrame prev = new NormalFrame(1, 0, List.of(3, 2), null, null, 5);
        NormalFrame current = new NormalFrame(2, 0, List.of(1, 4), prev, null, 0);

        assertThat(commonScoreStrategy.compute(current)).isEqualTo(10);
    }

    @DisplayName("파이널 프레임 점수 계산 성공")
    @Test
    void compute_finalFrame_success() {
        NormalFrame prev = new NormalFrame(9, 0, null, null, null, 90);
        FinalFrame current = new FinalFrame(10, 0, List.of(5, 5, 5), prev, null);

        assertThat(commonScoreStrategy.compute(current)).isEqualTo(105);
    }
}
