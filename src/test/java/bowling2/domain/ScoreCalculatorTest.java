package bowling2.domain;

import bowling2.domain.frame.NormalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
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

    @DisplayName("스트라이크나 스페어면 다음 프레임으로 넘어갈 때 pending처리")
    @Test
    void pendingOrCalculate_pending() {
        // given
        Queue<PendingFrame> queue = new LinkedList<>();
        scoreCalculator = new ScoreCalculator(new ScorePendingQueue(queue));

        int index = 2;
        NormalFrame currentFrame = new NormalFrame(index, 0, List.of(3, 7), null, null, 0);

        // when
        scoreCalculator.pendingOrCalculate(currentFrame);

        // then
        assertThat(queue.peek()).isEqualTo(PendingFrame.spare(index));
    }

    @DisplayName("스트라이크나 스페어가 아니면 다음 프레임으로 넘어갈 때 점수계산")
    @Test
    void pendingOrCalculate_calculate() {
        // given
        NormalFrame currentFrame = new NormalFrame(1, 3, List.of(3, 4), null, null, 0);

        // when
        scoreCalculator.pendingOrCalculate(currentFrame);

        // then
        assertThat(currentFrame.score()).isEqualTo(7);

    }
}
