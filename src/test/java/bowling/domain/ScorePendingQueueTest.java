package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

class ScorePendingQueueTest {

    @DisplayName("투구가 끝나면 큐에 있는 펜딩 건의 popCount를 -1한다.")
    @Test
    void minusPopCount() {
        // given
        Queue<PendingFrame> pendings = new LinkedList<>();
        pendings.add(new PendingFrame(1, 2));

        // when
        ScorePendingQueue queue = new ScorePendingQueue(pendings);
        queue.minusPopCount();

        // then
        assertThat(queue.peek().popCount()).isEqualTo(1);
    }

    @DisplayName("popCount = 0인 펜딩 건을 꺼낸다.")
    @Test
    void pop_popCountIs0() {
        // given
        Queue<PendingFrame> pendings = new LinkedList<>();
        pendings.add(new PendingFrame(1, 0));
        pendings.add(new PendingFrame(2, 1));

        // when
        ScorePendingQueue queue = new ScorePendingQueue(pendings);
        PendingFrame preparedPending = queue.getPreparedPending().get();

        // then
        assertThat(preparedPending.popCount()).isEqualTo(0);
    }

}
