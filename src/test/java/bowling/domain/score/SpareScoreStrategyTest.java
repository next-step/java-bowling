package bowling.domain.score;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SpareScoreStrategyTest {

    private SpareScoreStrategy spareScoreStrategy;

    @BeforeEach
    void setUp() {
        spareScoreStrategy = new SpareScoreStrategy();
    }

    @DisplayName("점수 계산 성공")
    @Test
    void compute_success() {
        NormalFrame prev = new NormalFrame(8, 0, null, null, null, 80);
        FinalFrame next = new FinalFrame(10, 0, List.of(10), null, null);
        NormalFrame current = new NormalFrame(9, 0, List.of(5, 5), prev, next, 0);

        assertThat(spareScoreStrategy.compute(current)).isEqualTo(100);
    }
}
