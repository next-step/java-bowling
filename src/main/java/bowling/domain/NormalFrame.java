package bowling.domain;

public class NormalFrame implements Frame {

    private static final int MIN_FRAME_NUMBER = 1;
    private static final int MAX_FRAME_NUMBER = 9;
    private final int number;
    private final Score score;

    public NormalFrame() {

        this(1);
    }

    public NormalFrame(final int number) {

        checkSize(number);
        this.number = number;
        this.score = new Score();
    }

    private void checkSize(final int number) {

        if (number < MIN_FRAME_NUMBER || number > MAX_FRAME_NUMBER) {
            throw new IllegalArgumentException(MIN_FRAME_NUMBER + "~" + MAX_FRAME_NUMBER + " 사이의 숫자만 가능합니다.");
        }
    }

    @Override
    public void pitch(final int number) {

        score.add(new Pin(number));
    }

    @Override
    public int getNumber() {

        return number;
    }

    @Override
    public boolean isEmpty() {

        return score.pins().isEmpty();
    }

    @Override
    public int pinNumber(int index) {

        return score.getPin(index);
    }

    @Override
    public int pinsSize() {

        return score.pins().size();
    }

    @Override
    public boolean canPitch() {

        return !score.frameEnd() && !status().strikeOrSpare();
    }

    @Override
    public Frame nextFrame() {

        if (number == MAX_FRAME_NUMBER) {
            return new LastFrame(number + 1);
        }

        return new NormalFrame(number + 1);
    }

    @Override
    public ScoreType status() {

        return score.status();
    }
}
