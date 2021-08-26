package bowling.step2.domain;

public class NormalFrame implements Frame {
    private int frameNumber;
    private Pin first;
    private Pin second;

    public NormalFrame(int frameNumber) {
        this.frameNumber = frameNumber;
        first = Pin.of(0);
        second = Pin.of(0);
    }

    public void pitch(int tryNo, int count) {
        validateCount(count);
        if (tryNo == 1) {
            first = Pin.of(count);
            return;
        }

        second = first.nextPitch(count);
    }

    private void validateCount(int count) {
        if (count > 10) {
            throw new RuntimeException("한번에 쓰러뜨릴 수 있는 볼링핀의 갯수는 10을 넘을 수 없습니다");
        }
    }

    public int sum() {
        return first.getCount() + second.getCount();
    }
}
