package bowling;

import java.util.Objects;

public class Pitching {

    private PitchingStatus pitchingStatus;
    private Pin pin;

    private Pitching(PitchingStatus pitchingStatus, Pin pin) {
        this.pitchingStatus = pitchingStatus;
        this.pin = pin;
    }

    public static Pitching of(PitchingStatus pitchingStatus, Pin pin) {
        return new Pitching(pitchingStatus, pin);
    }

    public static Pitching ofReady() {
        return new Pitching(PitchingStatus.Ready, Pin.ofMin());
    }

    public Pin getPin() {
        return pin;
    }

    public boolean isDone() {
        return pitchingStatus.isDone();
    }

    public void bowl(Pin pin) {
        if (isDone()) {
            throw new IllegalStateException("이미 투구가 완료된 상태 입니다.");
        }
        this.pin = Pin.of(pin);
        this.pitchingStatus = PitchingStatus.Done;
    }

    public boolean isClear() {
        return pin.equals(Pin.ofMax());
    }

    public boolean isOverMaxPins(Pin pin) {
        return this.pin.isOverMaxPins(pin);
    }

    public boolean isSpare(Pitching pitching) {
        return this.pin.isSpare(pitching.pin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pitching pitching = (Pitching) o;

        if (pitchingStatus != pitching.pitchingStatus) return false;
        return Objects.equals(pin, pitching.pin);
    }

    @Override
    public int hashCode() {
        int result = pitchingStatus != null ? pitchingStatus.hashCode() : 0;
        result = 31 * result + (pin != null ? pin.hashCode() : 0);
        return result;
    }
}
