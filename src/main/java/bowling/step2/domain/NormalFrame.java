package bowling.step2.domain;

public class NormalFrame implements Frame {
    private int frameNumber;
    private Pin first;
    private Pin second;

    private final int MAX = 10;

    private NormalFrame(int frameNumber) {
        this.frameNumber = frameNumber;
        first = Pin.of(0);
        second = Pin.of(0);
    }

    public static NormalFrame of(int frameNumber) {
        return new NormalFrame(frameNumber);
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
        if (count > MAX) {
            throw new RuntimeException("한번에 쓰러뜨릴 수 있는 볼링핀의 갯수는 " + MAX + "을 넘을 수 없습니다");
        }
    }

    public int total() {
        return first.count() + second.count();
    }
}
