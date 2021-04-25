package bowling.domain;

public class NormalFrame extends Frame {

    private static final int FIRST_NUMBER = 1;
    private static final int STRIKE_COUNT = 10;
    private static final int MAX_PITCH_ABLE_COUNT = 2;

    public static NormalFrame first() {
        return new NormalFrame(FIRST_NUMBER, new Pitches());
    }

    private NormalFrame(int number, Pitches pitches) {
        super(number, pitches);
    }

    @Override
    public void pitch(Pitch pitch) {
        validatePitch(pitch);
        pitches.add(pitch);
    }

    private void validatePitch(Pitch pitch) {
        if (pitch == null) {
            throw new IllegalArgumentException("투구 정보를 입력해 주세요.");
        }
        if (isFinished()) {
            throw new IllegalStateException("종료된 프레임입니다.");
        }
        if (spare() < pitch.value()) {
            throw new IllegalArgumentException("핀 처리횟수가 남은 핀수를 초과합니다. 투구정보를 확인해 주세요.");
        }
    }

    @Override
    public boolean isFinished() {
        return pitches.pinDownCount() == STRIKE_COUNT || pitches.count() == MAX_PITCH_ABLE_COUNT;
    }

    @Override
    public String printScoreBoard() {
        return null;
    }

    @Override
    int spare() {
        return STRIKE_COUNT - pitches.pinDownCount();
    }
}
