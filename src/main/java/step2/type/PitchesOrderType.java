package step2.type;

import step2.domain.BowlingPoints;

public enum PitchesOrderType {
    NONE,
    FIRST,
    SECOND,
    THIRD;

    public static final String ERROR_NO_SUCH_MATCH_TYPE = "적절한 타입을 찾을 수 없습니다.";

    public static PitchesOrderType currentType(BowlingPoints points) {
        return getType(points.size());
    }

    public static PitchesOrderType nextType(BowlingPoints points) {
        return getType(points.size() + 1);
    }

    public static PitchesOrderType getType(int source) {
        switch (source) {
            case 0:
                return NONE;
            case 1:
                return FIRST;
            case 2:
                return SECOND;
            case 3:
                return THIRD;
            default:
                throw new IllegalArgumentException(ERROR_NO_SUCH_MATCH_TYPE);
        }
    }
}
