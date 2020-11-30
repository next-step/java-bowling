package step2.domain;

import step2.type.PitchesOrderType;
import step2.type.ResultPitchesType;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static step2.domain.BowlingPoint.*;
import static step2.type.PitchesOrderType.*;

public class BowlingPoints {
    public static final String ERROR_NOT_PITCHES = "더 이상 투구할 수 없습니다.";
    public static final String ERROR_ALREADY_EXISTS_VALUE = "이미 존재하는 값을 추가할 수 없습니다.";

    public static final String WALL_DELIMITER = "|";
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

    public void push(int pitchesCount) throws IllegalArgumentException {

        BowlingPoint bowlingPoint = makeBowlingPoint(pitchesCount);

        PitchesOrderType type = PitchesOrderType.nextType(this);
        push(type, bowlingPoint);
    }

    private BowlingPoint makeBowlingPoint(int pitchesCount) {
        PitchesOrderType type = PitchesOrderType.getType(size());

        if (maxPitches == NORMAL_MAX_PITCHES) {
            return makePoint((current) -> current.equals(EMPTY_BOWLING_POINT), pitchesCount);
        }

        if (maxPitches == FINAL_MAX_PITCHES && type.equals(NONE)) {
            return BowlingPoint.of(pitchesCount);
        }

        if (maxPitches == FINAL_MAX_PITCHES
                && (type.equals(FIRST)
                || type.equals(SECOND)
                && getScore() >= STRIKE_VALUE)) {
            return makePoint((current) -> current.getPoint() == STRIKE_VALUE, pitchesCount);
        }

        throw new IllegalArgumentException("BowlingPoint를 생성할 수 없습니다.");
    }

    private BowlingPoint makePoint(Function<BowlingPoint, Boolean> function, int pitchesCount) {
        PitchesOrderType type = PitchesOrderType.getType(size());
        BowlingPoint current = get(type);
        Boolean result = function.apply(current);

        return result
                ? BowlingPoint.of(pitchesCount)
                : BowlingPoint.of(pitchesCount, current.getPoint());

    }


    public void push(PitchesOrderType type, BowlingPoint point) throws IllegalArgumentException {
        isAllowType(type);
        isValid();
        bowlingPoints.put(type, point);

        updateComplete();
    }

    private void updateComplete() {
        if (size() == maxPitches
                || (maxPitches == NORMAL_MAX_PITCHES && getScore(FIRST) >= STRIKE_VALUE)
                || (maxPitches == FINAL_MAX_PITCHES && size() == 2 && getScore(FIRST, SECOND) < STRIKE_VALUE)) {
            completed = true;
        }
    }

    private void isValid() {
        if (size() == maxPitches) {
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
        return Stream.of(FIRST, SECOND, THIRD)
                .filter(type-> !get(type).equals(EMPTY_BOWLING_POINT))
                .map(type-> get(type).getMark())
                .collect(Collectors.joining(WALL_DELIMITER));
    }


    private boolean isSpare(BowlingPoint point) {
        return point.getMark().equals(SPARE_MARK);
    }

    private boolean isStrike(BowlingPoint point) {
        return point.getMark().equals(STRIKE_MARK);
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
}
