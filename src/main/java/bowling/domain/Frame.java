package bowling.domain;

import java.util.*;
import java.util.concurrent.RecursiveTask;

public class Frame implements Iterable<RollingResult> {

    private final List<RollingResult> values = new ArrayList<>();
    private final FrameStrategy strategy;
    private Frame before;
    private Score score;

    public Frame(FrameStrategy strategy, Frame before) {
        this.strategy = strategy;
        this.before = before;
    }

    public static Frame createFirst() {
        return new Frame(new NormalStrategy(), null);
    }

    public Frame createNext() {
        return new Frame(new NormalStrategy(), this);
    }

    public Frame createFinal() {
        return new Frame(new FinalStrategy(), this);
    }

    public void bowling(int pinCount) {
        bowling(PinCount.of(pinCount));
    }

    public void bowling(PinCount pinCount) {
        if (isEnd()) {
            throw new IllegalStateException("프레임이 종료 되었습니다");
        }

        values.add(createRollingResult(pinCount));
    }

    private RollingResult createRollingResult(PinCount pinCount) {
        if (isFirstFrame()) {
            return RollingResult.createFirst(this, pinCount);
        }

        if (isFirstRolling()) {
            return before.getLastRollingResult().createNext(this, pinCount);
        }

        return getBeforeRollingResultInFrame().createNext(this, pinCount);
    }

    private boolean isFirstFrame() {
        return before == null;
    }

    public boolean isEnd() {
        return strategy.isFrameEnd(size(), beforeResult());
    }

    public int size() {
        return values.size();
    }

    public PinCount beforeCount() {
        if (isFirstRolling()) {
            return PinCount.of(0);
        }

        return getBeforeRollingResultInFrame().getPinCount();
    }

    private Result beforeResult() {
        if (isFirstRolling()) {
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

    private boolean isFirstRolling() {
        return size() == Result.FIRST_ROUND;
    }

    public Result getResult(int round) {
        return getRollingResult(round).getResult();
    }

    private RollingResult getLastRollingResult() {
        return getRollingResult(values.size() - 1);
    }

    public RollingResult getRollingResult(int round) {
        if (round < 0 || round > values.size() - 1) {
            throw new IllegalArgumentException("유효하지 않은 인덱스 입니다. " + round);
        }

        return values.get(round);
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
