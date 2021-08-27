package bowling.step2.domain;

public class Pin {
    private Count count = Count.NONE;

    private final int MAX = 10;

    private Pin(int count) {
        validatePitch(count);
        this.count = Count.of(count);
    }

    public static Pin of() {
        return new Pin(0);
    }

    public static Pin of(int count) {
        return new Pin(count);
    }

    private void validatePitch(int count) {
        if (sumOfPitchesOverTheMax(count)) {
            throw new RuntimeException("쓰러뜨린 핀의 개수는 10을 넘을 수 없습니다.");
        }
    }

    private boolean sumOfPitchesOverTheMax(int count) {
        return this.count.value() + count > MAX;
    }

    public Count count() {
        return count;
    }

    public int value() {
        return count.value();
    }
}
