package bowling.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Frame implements Iterable<RollingResult> {

    private final List<RollingResult> values = new ArrayList<>();
    private final FrameStrategy strategy;
    private Frame before;
    private Optional<Score> score = Optional.empty();

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

    public Optional<Score> getScore() {
        if (score.isPresent()) {
            return score;
        }

        getCurrentScore()
                .ifPresent(current -> this.score = Optional.of(getBeforeScore().add(current)));

        return score;
    }

    private Optional<Score> getCurrentScore() {
        if (canNotCalculateScore()) {
            return Optional.empty();
        }
        ;

        Score sum = values.stream()
                .map(r -> strategy.getScore(r).orElseThrow(() -> new IllegalArgumentException("점수를 조회할 수 없습니다")))
                .reduce(Score.of(0), Score::add);

        return Optional.of(sum);
    }

    private boolean canNotCalculateScore() {
        return values.stream()
                .anyMatch(r -> strategy.getScore(r).isEmpty());
    }

    public Score getBeforeScore() {
        if (before == null) {
            return Score.of(0);
        }

        return before.getScore()
                .orElseGet(() -> Score.of(0));
    }

    public boolean isEmpty() {
        return values.size() == 0;
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
