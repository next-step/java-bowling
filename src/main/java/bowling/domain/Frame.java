package bowling.domain;

import org.springframework.util.CollectionUtils;

import java.util.*;

public class Frame implements Iterable<RollingResult> {

    private final List<RollingResult> values = new ArrayList<>();
    private final FrameStrategy strategy;
    private Frame before;
    private Frame after;
    private Score score;

    public Frame(FrameStrategy strategy, Frame before) {
        this.strategy = strategy;
        this.before = before;
    }

    public static Frame createFirst() {
        return new Frame(new NormalStrategy(), null);
    }

    public Frame createNext() {
        this.after = new Frame(new NormalStrategy(), this);
        return this.after;
    }

    public Frame createFinal() {
        this.after = new Frame(new FinalStrategy(), this);
        return this.after;
    }

    public void bowling(int pinCount) {
        bowling(PinCount.of(pinCount));
    }

    public void bowling(PinCount pinCount) {
        if (isEnd()) {
            throw new IllegalStateException("프레임이 종료 되었습니다");
        }

        values.add(getCurrentRollingResult(pinCount));
    }

    private RollingResult getCurrentRollingResult(PinCount pinCount) {
        if (before == null) {
            return RollingResult.createFirst(this, pinCount);
        }

        if (values.isEmpty()) {
            return before.getLastRollingResult().createNext(this, pinCount);
        }

        return getBeforeRollingResultInFrame().createNext(this, pinCount);
    }

    public boolean isEnd() {
        return strategy.isFrameEnd(size(), beforeResult());
    }

    public int size() {
        return values.size();
    }

    public PinCount beforeCount() {
        if (isFirstRound()) {
            return PinCount.of(0);
        }

        return getBeforeRollingResultInFrame().getPinCount();
    }

    private Result beforeResult() {
        if (isFirstRound()) {
            return Result.NONE;
        }

        return getBeforeRollingResultInFrame().getResult();
    }

    private RollingResult getBeforeRollingResultInFrame() {
        if (values.isEmpty()) {
            new IllegalStateException("이전 결과 없음");
        }

        return values.get(values.size() - 1);
    }

    private boolean isFirstRound() {
        return size() == Result.FIRST_ROUND;
    }

    public Result getResult(int round) {
        return getBowling(round).getResult();
    }

    public boolean isFinal() {
        return strategy.isFinal();
    }

    public RollingResult getBowling(int round) {
        if (round < 0 || round > values.size() - 1) {
            throw new IllegalArgumentException("유효하지 않은 인덱스 입니다. " + round);
        }

        return values.get(round);
    }

    private RollingResult getLastRollingResult() {
        return getBowling(values.size() - 1);
    }

    public Score getScore() {
        if (score != null) {
            return score;
        }

        score = getBeforeScore().add(getCurrentScore());
        return score;
    }

    private Score getCurrentScore() {
        return values.stream()
                .map(v -> v.getScore())
                .reduce(Score.of(0), Score::add);
    }

    public Score getBeforeScore() {
        return Optional.ofNullable(before)
                .map(Frame::getScore)
                .orElseGet(() -> Score.of(0));
    }

    //=============================================================

    @Override
    public String toString() {
        return "Frame{" +
                "values=" + values +
                ", strategy=" + strategy +
                '}';
    }

    @Override
    public Iterator<RollingResult> iterator() {
        return values.iterator();
    }

}
