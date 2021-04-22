package bowling.domain;

import java.util.Objects;

public class NormalFrameBowls implements FrameBowls{
    private static final int BOUND_MAX = 10;
    private static final String CHECK_BOUND = "총 쓰러뜨린 개수가 10을 초과하는 지 확인해주세요.";
    private static final String CANNOT_THROW_SECOND_BOWL = "초구가 스트라이크이기 때문에 2구를 던질 수 없습니다.";

    private final Bowl firstBowl;
    private final Bowl secondBowl;

    public NormalFrameBowls() {
        this(new Bowl(false), new Bowl(false));
    }

    private NormalFrameBowls(Bowl firstBowl, Bowl secondBowl) {
        checkBound(firstBowl, secondBowl);
        this.firstBowl = firstBowl;
        this.secondBowl = secondBowl;
    }

    private void checkBound(Bowl firstBowl, Bowl secondBowl) {
        if (firstBowl.knockedDownPinCount().plus(secondBowl.knockedDownPinCount()) > BOUND_MAX) {
            throw new IllegalArgumentException(CHECK_BOUND);
        }
    }

    @Override
    public FrameBowls firstThrow(int firstPinCount) {
        return new NormalFrameBowls(new Bowl(firstPinCount, true), secondBowl);
    }

    @Override
    public FrameBowls secondThrow(int secondPinCount) {
        if (isFirstBowlStrike()) {
            throw new IllegalArgumentException(CANNOT_THROW_SECOND_BOWL);
        }

        return new NormalFrameBowls(firstBowl, new Bowl(secondPinCount, true));
    }

    public boolean isFirstBowlStrike() {
        return firstBowl.isStrike();
    }

    public boolean isSecondBowlSpare() {
        return firstBowl.plusPinCountOf(secondBowl) == BOUND_MAX;
    }

    @Override
    public int totalPinCount() {
        return firstBowl.plusPinCountOf(secondBowl);
    }

    @Override
    public PinCount firstPinCount() {
        return firstBowl.knockedDownPinCount();
    }

    @Override
    public PinCount secondPinCount() {
        return secondBowl.knockedDownPinCount();
    }

    @Override
    public boolean isFirstBowlThrown() {
        return firstBowl.isThrown();
    }

    @Override
    public boolean isSecondBowlThrown() {
        return secondBowl.isThrown();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrameBowls that = (NormalFrameBowls) o;
        return Objects.equals(firstBowl, that.firstBowl) &&
                Objects.equals(secondBowl, that.secondBowl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstBowl, secondBowl);
    }
}
