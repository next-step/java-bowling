package bowling.domain;

import ch.qos.logback.classic.net.SocketReceiver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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

    public static Frame createNormal() {
        return new Frame(new NormalStrategy(), null);
    }

    public Frame createNext() {
        this.after = new Frame(new NormalStrategy(), this);
        return this.after;
    }

    @Deprecated
    public static Frame createFinal() {
        return new Frame(new FinalStrategy(), null);
    }

    public Frame createFinal2() {
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
        return getBeforeBowling()
                .map(b -> b.createNext(this, pinCount))
                .orElseGet(() -> RollingResult.createFirst(this, pinCount));
    }

    public boolean isEnd() {
        return strategy.isFrameEnd(getRound(), beforeResult());
    }

    public int getRound() {
        return values.size();
    }

    public PinCount beforeCount() {
        return getBeforeBowling().get().getPinCount();
    }

    private Result beforeResult() {
        if (isFirstRound()) {
            return Result.NONE;
        }

        return getBeforeBowling().map(RollingResult::getResult)
                .orElseThrow(() -> new IllegalStateException("이전 결과 없음"));
    }

    private Optional<RollingResult> getBeforeBowling() {
        if (values.size() >= 1) {
            return Optional.of(values.get(values.size() - 1));
        }

        return Optional.ofNullable(before)
                .map(Frame::getLastRollingResult);
    }

    private boolean isFirstRound() {
        return getRound() == Result.FIRST_ROUND;
    }

    public Result getResult(int round) {
        return getBowling(round).getResult();
    }

    public boolean isFinal() {
        return strategy.isFinal();
    }

    public RollingResult getBowling(int round) {
        if (round > values.size() -1) {
            throw new IllegalArgumentException("유효하지 않은 인덱스 입니다. " + round);
        }

        return values.get(round);
    }

    private RollingResult getLastRollingResult() {
        return getBowling(values.size()-1);
    }

    public boolean isStrike() {
        return values.stream()
                .anyMatch(r -> r.getResult().isStrike());
    }

    public boolean isSpare() {
        return values.stream()
                .anyMatch(r -> r.getResult().isSpare());
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

    @Deprecated
    public Score getSumPintCount() {
        return Score.of(values.stream()
                        .mapToInt(RollingResult::getCount)
                        .sum());
    }

    public PinCount getAfterPinCount() {
        return Optional.ofNullable(after)
                .map(f -> f.getFirstPinCount())
                .orElseGet(() -> PinCount.of(0));
    }

    @Deprecated
    private PinCount getFirstPinCount() {
        if (values.isEmpty()) {
            return PinCount.of(0);
        }
        return values.get(0).getPinCount();
    }

    public PinCount getAfterAfterPinCount() {
        return Optional.ofNullable(after)
                .map(f -> f.getSecondPinCount())
                .orElseGet(() -> PinCount.of(0));
    }

    @Deprecated
    private PinCount getSecondPinCount() {
        if (isEnd() && values.size() == 1) { // 현재 스트라이크인 경우
            return after.getFirstPinCount(); // 다음프레임의 첫번째 투구를 찾는다
        }

        //

        return Optional.ofNullable(values.get(1))
                .map(RollingResult::getPinCount)
                .orElseGet(() -> after.getAfterPinCount());
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
