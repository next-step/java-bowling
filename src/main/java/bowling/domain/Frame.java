package bowling.domain;

public class Frame {
    private Hit first;
    private Hit second;

    public void firstThrow(Hit first) {
        this.first = first;
        if (first.isMax()) {
            second = new Hit(0);
        }
    }

    public void secondThrow(Hit second) {
        this.second = second;
    }

    public FrameStatus getFirstStatus() {
        if (first == null) {
            return FrameStatus.BEFORE;
        }
        if (first.isMax()) {
            return FrameStatus.STRIKE;
        }
        if (first.isMin()) {
            return FrameStatus.GUTTER;
        }
        return FrameStatus.MISS;
    }

    public FrameStatus getSecondStatus() {
        if (first == null || second == null) {
            return FrameStatus.BEFORE;
        }
        if (first.isMax()) {
            return FrameStatus.SKIP;
        }
        Hit sum = first.plus(second);
        if (sum.isMax()) {
            return FrameStatus.SPARE;
        }
        if (second.isMin()) {
            return FrameStatus.GUTTER;
        }
        return FrameStatus.MISS;
    }

    public boolean isEnd() {
        return first != null && second != null;
    }

    public Hit getFirstHit() {
        return first;
    }

    public Hit getSecondHit() {
        return second;
    }
}
