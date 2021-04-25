package bowling.domain;

public class NormalFrame extends Frame {

    private static final int FIRST_NUMBER = 1;
    private static final int LAST_NUMBER = 9;
    private static final int STRIKE_COUNT = 10;
    private static final int MAX_PITCH_ABLE_COUNT = 2;

    private Frame next;

    private NormalFrame(int number, NormalFrame before) {
        super(number, before);
    }

    private NormalFrame(int number, Pitches pitches) {
        super(number, pitches);
    }

    public static NormalFrame first() {
        return new NormalFrame(FIRST_NUMBER, new Pitches());
    }

    public Frame next() {
        validateCreateNextFrame();
        if (hasNext()) {
            return this.next;
        }
        return createNextFrame();
    }

    private void validateCreateNextFrame() {
        if (!isFinished()) {
           throw new IllegalStateException("종료되지 않은 프레임입니다. 다음 프레임을 시작할 수 없습니다.");
        }
    }

    private Frame createNextFrame() {
        // TODO: isLast 만족 시 FinalFrame 리턴
        this.next = new NormalFrame(number() + 1, this);
        return this.next;
    }

    private boolean isLast() {
        return number() == LAST_NUMBER;
    }

    private boolean hasNext() {
        return this.next != null;
    }

    @Override
    public void pitch(Pitch pitch) {
        validatePitch(pitch);
        pitches().add(pitch);
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
        return pitches().pinDownCount() == STRIKE_COUNT || pitches().count() == MAX_PITCH_ABLE_COUNT;
    }

    @Override
    public String printScoreBoard() {
        return null;
    }

    @Override
    int spare() {
        return STRIKE_COUNT - pitches().pinDownCount();
    }
}
