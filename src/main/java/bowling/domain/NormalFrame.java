package bowling.domain;

public class NormalFrame {

    private final Pins pins = new Pins();
    private final Records records = new Records();

    public void throwBall(PitchResult pitchResult) {
        if (isEnded()) {
            throw new IllegalStateException("이미 프레임이 종료된 상태입니다.");
        }

        records.add(pitchResult);
        pins.knockDown(pitchResult);
    }

    public boolean isEnded() {
        return records.isStrike() || records.isMissed() || records.isSpare();
    }

}
