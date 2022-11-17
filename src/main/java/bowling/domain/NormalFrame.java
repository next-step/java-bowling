package bowling.domain;

public class NormalFrame implements Frame {
    private Hit first;
    private Hit second;

    @Override
    public void firstThrow(Hit hit) {
        this.first = hit;
        if (hit.isMax()) {
            second = new Hit(0);
        }
    }

    @Override
    public void secondThrow(Hit hit) {
        this.second = hit;
    }

    @Override
    public void thirdThrow(Hit hit) {
        throw new IllegalStateException("실행할 수 없는 메소드를 실행했습니다.");
    }

    @Override
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

    @Override
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

    @Override
    public FrameStatus getThirdStatus() {
        return FrameStatus.SKIP;
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
        return new Hit(0);
    }

    @Override
    public boolean isEnd() {
        return first != null && second != null;
    }
}
