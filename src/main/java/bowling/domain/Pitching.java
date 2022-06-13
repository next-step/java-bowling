package bowling.domain;

public class Pitching {
    public static final int MAXIMUM = 10;
    public static final int MINIMUM = 0;

    private final int pitchingNumber;

    public Pitching(int pitchingNumber) {
        if (MINIMUM > pitchingNumber || MAXIMUM < pitchingNumber) {
            throw new IllegalArgumentException(pitchingNumber + "는 유효범위가 아닙니다.");
        }
        this.pitchingNumber = pitchingNumber;
    }

    public boolean isStrike() {
        return pitchingNumber == MAXIMUM;
    }

    public int getPitchingNumber() {
        return pitchingNumber;
    }
}
