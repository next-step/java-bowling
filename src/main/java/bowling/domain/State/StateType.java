package bowling.domain.State;

public enum StateType {
//    STRIKE((totalPinCounts, tryCount) -> totalPinCounts == 10 && tryCount == 1),
//    SPARE((totalPinCounts, tryCount) -> totalPinCounts == 10 && tryCount == 2),
//    MISS((totalPinCounts, tryCount) -> totalPinCounts >= 0 && totalPinCounts < 10 && tryCount == 2),
//    GUTTER((totalPinCounts, tryCount) -> totalPinCounts == 0 && tryCount == 2),
//    NONE((totalPinCounts, tryCount) -> false);
//
//    private final BiFunction<Integer, Integer, Boolean> scoreDecideFunction;
//
//    FrameStateType(BiFunction<Integer, Integer, Boolean> scoreDecideFunction) {
//        this.scoreDecideFunction = scoreDecideFunction;
//    }
//
//    public static FrameStateType of(int totalPinCounts, int tryCount) {
//        return Arrays.stream(values())
//                .filter(frameScoreResult -> frameScoreResult.scoreDecideFunction.apply(totalPinCounts,tryCount))
//                .findFirst()
//                .orElse(NONE);
//    }
    STRIKE,
    SPARE,
    MISS,
    GUTTER,
    NONE;
}
