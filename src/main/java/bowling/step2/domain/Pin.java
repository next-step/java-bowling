package bowling.step2.domain;

public class Pin {
    private final int count;

    private final int MAX = 10;

    private Pin(int count) {
        validatePitch(count);
        this.count = count;
    }

    public static Pin of(int count) {
        return new Pin(count);
    }

    public Pin nextPitch(int count) {
        validatePitch(count);
        return Pin.of(count);
    }

    private void validatePitch(int count) {
        if (sumOfPitchesOverTheMax(count)) {
            throw new RuntimeException("쓰러뜨린 핀의 개수는 10을 넘을 수 없습니다.");
        }
    }

    private boolean sumOfPitchesOverTheMax(int count) {
        return this.count + count > MAX;
    }

    public int count() {
        return count;
    }
}
