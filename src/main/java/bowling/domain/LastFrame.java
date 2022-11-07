package bowling.domain;

public class LastFrame implements Frame {

    private static final int FRAME_THRESHOLD = 10;

    private final int number;
    private final Score score;

    public LastFrame(final int number) {

        validate(number);
        this.number = number;
        this.score = new Score();
    }

    private void validate(final int number) {

        if (number > FRAME_THRESHOLD) {
            throw new IllegalArgumentException("마지막 프레임의 번호를 초과하였습니다.");
        }
    }

    @Override
    public boolean canPitch() {

        return score.pitches() < 2 || !status().strikeOrSpare();
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
    public int getNumber() {

        return number;
    }

    @Override
    public void pitch(int number) {

        score.add(new Pin(number));
    }

    @Override
    public ScoreType status() {

        return score.status();
    }

    @Override
    public Frame nextFrame() {

        throw new IllegalStateException("마지막 프레임입니다.");
    }
}