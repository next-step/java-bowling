package bowling.step2.domain;

public class LastFrame implements Frame {
    private Pin first;
    private Pin second;
    private Pin additional;

    private final int MAX = 10;

    public LastFrame() {
        first = Pin.of(0);
        second = Pin.of(0);
        additional = Pin.of(0);
    }

    @Override
    public void pitch(TryNo tryNo, int count) {
        validateCount(count);

        if (tryNo == TryNo.FIRST) {
            first = Pin.of(count);
            return;
        }

        if (tryNo == TryNo.SECOND) {
            validateSumWithFirst(count);
            second = Pin.of(count);
            return;
        }

        validateRightToPitchAdditional();
        additional = Pin.of(count);
    }

    private void validateRightToPitchAdditional() {
        if (unavailableToPitchAdditional()) {
            throw new RuntimeException("추가 투구를 할 수 없습니다");
        }
    }

    @Override
    public int total() {
        return first.count() + second.count() + additional.count();
    }

    private void validateCount(int count) {
        if (countIsOverMax(count)) {
            throw new RuntimeException("한번에 쓰러뜨릴 수 있는 볼링핀의 갯수는 " + MAX + "을 넘을 수 없습니다");
        }
    }

    private void validateSumWithFirst(int count) {
        if (first.count() == MAX) return;

        validateCount(first.count() + count);
    }

    private boolean countIsOverMax(int count) {
        return count > MAX;
    }

    private boolean unavailableToPitchAdditional() {
        return total() < MAX;
    }

}
