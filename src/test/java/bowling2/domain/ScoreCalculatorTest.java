package bowling2.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;


class ScoreCalculatorTest {

    private ScoreCalculator scoreCalculator;

    @BeforeEach
    void setUp() {
        scoreCalculator = new ScoreCalculator();
    }

    @DisplayName("1회 투구 후 처리할 pending 건이 없는 경우 popCount - 1만 한다.")
    @Test
    void findPending_success() {
        // given
        Queue<PendingFrame> queue = new LinkedList<>();
        queue.add(PendingFrame.strike(2));
        scoreCalculator = new ScoreCalculator(new ScorePendingQueue(queue));

        // when
        Optional<PendingFrame> pending = scoreCalculator.findPreparedPending();

        // then
        assertThat(pending).isEmpty();
        assertThat(queue.peek().popCount()).isEqualTo(1);
    }

    @DisplayName("다음 프레임으로 넘어갈 때 점수계산을 바로할지 pending처리할지 정한 뒤 처리한다.")
    @Test
    void pendingOrCalculate() {

    }
}
