package bowling.domain;

import java.util.List;

public class NormalFrame extends Frame {

    public static final int FIRST_NUMBER = 1;
    private static final int LAST_NUMBER = 9;

    private Frame next;

    private NormalFrame(int number) {
        super(number);
    }

    private NormalFrame(int number, Pitches pitches) {
        super(number, pitches);
    }

    public static NormalFrame first() {
        return new NormalFrame(FIRST_NUMBER, new Pitches());
    }

    @Override
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
        this.next = createNextFrame(isLast());
        return this.next;
    }

    private Frame createNextFrame(boolean isLast) {
        if (isLast) {
            return new FinalFrame(number() + 1);
        }
        return new NormalFrame(number() + 1);
    }

    private boolean isLast() {
        return number() == LAST_NUMBER;
    }

    private boolean hasNext() {
        return this.next != null;
    }

    @Override
    public void pitch(Pitch pitch) {
        pitches().add(pitch);
    }

    @Override
    public boolean isFinished() {
        return pitches().isFinished();
    }

    @Override
    public List<String> getScoreBoards() {
        return pitches().getScoreBoards();
    }

}
