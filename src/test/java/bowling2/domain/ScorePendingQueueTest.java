package bowling2.domain;

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
        pendings.add(PendingFrame.strike(1));

        // when
        ScorePendingQueue queue = new ScorePendingQueue(pendings);
        queue.minusPopCount();

        // then
        assertThat(queue.peek().popCount()).isEqualTo(1);
    }
}
