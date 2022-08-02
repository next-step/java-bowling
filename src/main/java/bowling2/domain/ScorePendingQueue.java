package bowling2.domain;

import java.util.LinkedList;
import java.util.Objects;
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
        if (queue.isEmpty()) {
            return Optional.empty();
        }
        if (queue.peek().popCount() == 0) {
            return Optional.of(queue.poll());
        }
        return Optional.empty();
    }

    public void add(PendingFrame pendingFrame) {
        queue.add(pendingFrame);
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

    public static PendingFrame spare(int index) {
        return new PendingFrame(index, 1);
    }

    public void minusPopCount() {
        popCount--;
    }

    public int popCount() {
        return popCount;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PendingFrame that = (PendingFrame) o;
        return getIndex() == that.getIndex() && popCount == that.popCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIndex(), popCount);
    }
}
