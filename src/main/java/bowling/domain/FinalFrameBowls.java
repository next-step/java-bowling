package bowling.domain;

import java.util.Objects;

public class FinalFrameBowls implements FrameBowls {
    private static final int SPARE = 10;
    private static final String CHECK_SPARE_BOUND = "총 쓰러뜨린 개수가 10을 초과하는 지 확인해주세요.";
    private static final String CANNOT_THROW_THIRD_BOWL = "초구 스트라이크 또는 2구 스페어가 아니기 때문에 3구를 던질 수 없습니다.";
    private static final String CHECK_SECOND_SPARE_BOUND = "2구, 3구에서 총 쓰러뜨린 개수가 10개를 초과하는 지 확인해주세요.";

    private final Bowl firstBowl;
    private final Bowl secondBowl;
    private final Bowl thirdBowl;

    public FinalFrameBowls() {
        this(new Bowl(false), new Bowl(false), new Bowl(false));
    }

    private FinalFrameBowls(Bowl firstBowl, Bowl secondBowl, Bowl thirdBowl) {
        this.firstBowl = firstBowl;
        this.secondBowl = secondBowl;
        this.thirdBowl = thirdBowl;
    }

    @Override
    public FrameBowls firstThrow(int firstPinCount) {
        return new FinalFrameBowls(new Bowl(firstPinCount, true), secondBowl, thirdBowl);
    }

    @Override
    public FrameBowls secondThrow(int secondPinCount) {
        if (!isFirstBowlStrike() && firstBowl.knockedDownPinCount().plus(secondPinCount) > SPARE) {
            throw new IllegalArgumentException(CHECK_SPARE_BOUND);
        }
        return new FinalFrameBowls(firstBowl, new Bowl(secondPinCount, true), thirdBowl);
    }

    public FrameBowls thirdThrow(int thirdPinCount) {
        if (!isFirstBowlStrike() && !isSecondBowlSpare()) {
            throw new IllegalArgumentException(CANNOT_THROW_THIRD_BOWL);
        }

        if (isFirstBowlStrike() && !isSecondBowlStrike() && secondBowl.knockedDownPinCount().plus(thirdPinCount) > SPARE) {
            throw new IllegalArgumentException(CHECK_SECOND_SPARE_BOUND);
        }

        return new FinalFrameBowls(firstBowl, secondBowl, new Bowl(thirdPinCount, true));
    }

    @Override
    public boolean isFirstBowlStrike() {
        return firstBowl.isStrike();
    }

    @Override
    public boolean isSecondBowlSpare() {
        return !isFirstBowlStrike() && firstBowl.plusPinCountOf(secondBowl) == SPARE;
    }

    protected boolean isSecondBowlStrike() {
        return secondBowl.isStrike();
    }

    @Override
    public int totalPinCount() {
        return firstBowl.plusPinCountOf(secondBowl, thirdBowl);
    }

    @Override
    public PinCount firstPinCount() {
        return firstBowl.knockedDownPinCount();
    }

    @Override
    public PinCount secondPinCount() {
        return secondBowl.knockedDownPinCount();
    }

    public PinCount thirdPinCount() {
        return thirdBowl.knockedDownPinCount();
    }

    @Override
    public boolean isFirstBowlThrown() {
        return firstBowl.isThrown();
    }

    @Override
    public boolean isSecondBowlThrown() {
        return secondBowl.isThrown();
    }

    public boolean isThirdBowlThrown() {
        return thirdBowl.isThrown();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrameBowls that = (FinalFrameBowls) o;
        return Objects.equals(firstBowl, that.firstBowl) &&
                Objects.equals(secondBowl, that.secondBowl) &&
                Objects.equals(thirdBowl, that.thirdBowl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstBowl, secondBowl, thirdBowl);
    }
}
