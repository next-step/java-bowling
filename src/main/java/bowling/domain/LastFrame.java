package bowling.domain;

public class LastFrame implements Frame {
    private Hit first;
    private Hit second;
    private Hit third;

    @Override
    public void firstThrow(Hit hit) {
        this.first = hit;
    }

    @Override
    public void secondThrow(Hit hit) {
        this.second = hit;
    }

    @Override
    public void thirdThrow(Hit hit) {
        this.third = hit;
    }

    @Override
    public FrameStatus getFirstStatus() {
        return getNormalStatus(first);
    }

    @Override
    public FrameStatus getSecondStatus() {
        if (first == null || second == null) {
            return FrameStatus.BEFORE;
        }
        if (first.isMax()) {
            return getNormalStatus(second);
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

    @Override
    public FrameStatus getThirdStatus() {
        if (first == null || second == null || third == null) {
            return FrameStatus.BEFORE;
        }
        if (!canPlayThird()) {
            return FrameStatus.SKIP;
        }
        if (third.isMax()) {
            return FrameStatus.STRIKE;
        }
        if (third.isMin()) {
            return FrameStatus.GUTTER;
        }
        if (second.isMax()) {
            return FrameStatus.MISS;
        }
        Hit sum = second.plus(third);
        if (sum.isMax()) {
            return FrameStatus.SPARE;
        }
        return FrameStatus.MISS;
    }

    private FrameStatus getNormalStatus(Hit hit) {
        if (hit == null) {
            return FrameStatus.BEFORE;
        }
        if (hit.isMax()) {
            return FrameStatus.STRIKE;
        }
        if (hit.isMin()) {
            return FrameStatus.GUTTER;
        }
        return FrameStatus.MISS;
    }

    @Override
    public Hit getFirstHit() {
        return first;
    }

    @Override
    public Hit getSecondHit() {
        return second;
    }

    @Override
    public Hit getThirdHit() {
        return third;
    }

    @Override
    public boolean isEnd() {
        if (first == null || second == null) {
            return false;
        }
        if (third != null) {
            return true;
        }
        return !canPlayThird();
    }

    private boolean canPlayThird() {
        return getFirstStatus() == FrameStatus.STRIKE || getSecondStatus() == FrameStatus.SPARE;
    }
}
