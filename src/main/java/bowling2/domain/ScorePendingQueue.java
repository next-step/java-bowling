package bowling2.domain;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class ScorePendingQueue {
    private Queue<PendingFrame> queue = new LinkedList<>();

    public ScorePendingQueue() {
    }

    public ScorePendingQueue(Queue<PendingFrame> queue) {
        this.queue = queue;
    }

    public void minusPopCount() {
        queue.forEach(PendingFrame::minusPopCount);
    }

    public PendingFrame peek() {
        return queue.peek();
    }

    public Optional<PendingFrame> getPreparedPending() {
        return queue.stream()
                .filter(pendingFrame -> pendingFrame.popCount() == 0)
                .findFirst();
    }
}

class PendingFrame {
    private int index;
    private int popCount;

    public PendingFrame(int index, int popCount) {
        this.index = index;
        this.popCount = popCount;
    }

    public static PendingFrame strike(int index) {
        return new PendingFrame(index, 2);
    }

    public void minusPopCount() {
        popCount--;
    }

    public int popCount() {
        return popCount;
    }
}
