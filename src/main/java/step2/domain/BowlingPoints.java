package step2.domain;

import step2.type.PitchesOrderType;
import step2.type.ResultPitchesType;

import java.util.*;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static step2.domain.BowlingPoint.EMPTY_BOWLING_POINT;
import static step2.type.PitchesOrderType.*;

public class BowlingPoints {
    public static final String ERROR_NOT_PITCHES = "더 이상 투구할 수 없습니다.";
    public static final String ERROR_ALREADY_EXISTS_VALUE = "이미 존재하는 값을 추가할 수 없습니다.";
    public static final String ERROR_NOT_CREATE_OBJECT = "BowlingPoint 를 생성할 수 없습니다.";
    public static final int ZERO_SCORE = 0;
    public static final int NORMAL_MAX_PITCHES = 2;
    public static final int FINAL_MAX_PITCHES = 3;
    public static final int STRIKE_VALUE = 10;

    private final Map<PitchesOrderType, BowlingPoint> bowlingPoints;
    private boolean completed;
    private final int maxPitches;

    public BowlingPoints(Map<PitchesOrderType, BowlingPoint> bowlingPoints, int maxPitches, boolean completed) {
        this.bowlingPoints = bowlingPoints;
        this.completed = completed;
        this.maxPitches = maxPitches;
    }

    public static BowlingPoints of(int maxPitches) {
        return new BowlingPoints(new HashMap<>(), maxPitches, false);
    }

    public static BowlingPoints of(BowlingPoint bowlingPoint, int maxPitches) {
        return new BowlingPoints(new HashMap<PitchesOrderType, BowlingPoint>() {{
            put(FIRST, bowlingPoint);
        }}, maxPitches, false);
    }

    public BowlingPoints push(int pitchesCount) throws IllegalArgumentException {
        BowlingPoint bowlingPoint = makeBowlingPoint(pitchesCount);
        PitchesOrderType type = PitchesOrderType.nextType(this);

        return push(type, bowlingPoint);
    }

    private BowlingPoint makeBowlingPoint(int pitchesCount) {
        PitchesOrderType type = PitchesOrderType.getType(size());

        if (isNormalType()) {
            return makePoint((current) -> current.equals(EMPTY_BOWLING_POINT), pitchesCount);
        }

        if (isFinalFirst(type)) {
            return BowlingPoint.of(pitchesCount);
        }

        if (isAllowFinalCreate(type)) {
            return makePoint((current) -> current.getPoint() == STRIKE_VALUE || getScore() >= 10, pitchesCount);
        }

        throw new IllegalArgumentException(ERROR_NOT_CREATE_OBJECT);
    }

    private boolean isNormalType() {
        return maxPitches == NORMAL_MAX_PITCHES;
    }

    private boolean isFinalFirst(PitchesOrderType type) {
        return isFinalType() && type.equals(NONE);
    }

    private boolean isFinalType() {
        return maxPitches == FINAL_MAX_PITCHES;
    }

    private boolean isAllowFinalCreate(PitchesOrderType type) {
        return isFinalType() && (type.equals(FIRST) || type.equals(SECOND) && getScore() >= STRIKE_VALUE);
    }

    private BowlingPoint makePoint(Function<BowlingPoint, Boolean> function, int pitchesCount) {
        PitchesOrderType type = PitchesOrderType.getType(size());
        BowlingPoint current = get(type);
        Boolean result = function.apply(current);

        if (result) {
            return BowlingPoint.of(pitchesCount);
        }

        return BowlingPoint.of(pitchesCount, current.getPoint());
    }


    public BowlingPoints push(PitchesOrderType type, BowlingPoint point) throws IllegalArgumentException {
        isAllowType(type);
        isValid();
        bowlingPoints.put(type, point);
        updateComplete();

        return this;
    }

    private void updateComplete() {
        if (isMaximumSize() || isCompleteConditionByNormal() || isCompleteConditionByFinal()) {
            completed = true;
        }
    }

    private boolean isCompleteConditionByFinal() {
        return isFinalType()
                && size() == 2
                && getScore(FIRST, SECOND) < STRIKE_VALUE;
    }

    private boolean isCompleteConditionByNormal() {
        return isNormalType()
                && getScore(FIRST) >= STRIKE_VALUE;
    }

    private boolean isMaximumSize() {
        return size() == maxPitches;
    }

    private void isValid() {
        if (isMaximumSize()) {
            throw new IllegalArgumentException(ERROR_NOT_PITCHES);
        }
    }

    private void isAllowType(PitchesOrderType type) {
        if (Objects.nonNull(bowlingPoints.get(type))) {
            throw new IllegalArgumentException(ERROR_ALREADY_EXISTS_VALUE);
        }
    }

    public int size() {
        return bowlingPoints.size();
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getScore() {
        return bowlingPoints.values()
                .stream()
                .map(BowlingPoint::getPoint)
                .reduce(Integer::sum)
                .orElse(ZERO_SCORE);
    }

    public ResultPitchesType getType(PitchesOrderType first, PitchesOrderType second) {
        return ResultPitchesType.getType(get(first).getPoint(), get(second).getPoint());
    }

    private BowlingPoint get(PitchesOrderType type) {
        return Optional.ofNullable(bowlingPoints.get(type))
                .orElse(EMPTY_BOWLING_POINT);
    }

    public int getScore(PitchesOrderType type) {
        return get(type).getPoint();
    }


    public String getMark() {
        BowlingSymbol.Builder builder = BowlingSymbol.Builder(maxPitches, size());
        if (size() >= 1) {
            builder.firstPoint(get(FIRST).getPoint());
        }
        if (size() >= 2) {
            builder.secondPoint(get(SECOND).getPoint());
        }
        if (size() == 3) {
            builder.thirdPoint(get(THIRD).getPoint());
        }

        BowlingSymbol symbol = builder.build();
        return symbol.getSymbol();
    }

    public int getScore(PitchesOrderType... types) {
        List<PitchesOrderType> pitchesOrderTypes = asList(types);

        return bowlingPoints.entrySet()
                .stream()
                .filter(entry -> pitchesOrderTypes.contains(entry.getKey()))
                .map(entry -> entry.getValue().getPoint())
                .reduce(Integer::sum)
                .orElse(ZERO_SCORE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingPoints that = (BowlingPoints) o;
        return completed == that.completed &&
                maxPitches == that.maxPitches &&
                Objects.equals(bowlingPoints, that.bowlingPoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bowlingPoints, completed, maxPitches);
    }
}
