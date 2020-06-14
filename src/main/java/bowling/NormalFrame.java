package bowling;

public class NormalFrame implements Frame{

    private final static int MAX_ROUND = 2;

    private final int position;

    private int numberOfDownPin;
    private int round;

    private NormalFrame(int position) {
        this.position = position;
    }

    static NormalFrame first() {
        return new NormalFrame(0);
    }

    NormalFrame next() {
        return new NormalFrame(this.position +1);
    }

    @Override
    public String play(int pin) {
        if (!hasNextRound()) {
            throw new IllegalStateException("이미 종료된 frame입니다.");
        }

        this.round++;
        this.numberOfDownPin += pin;

        if (pin == 0) {
            return "-";
        }

        if (this.round == 1 && this.numberOfDownPin == 10) {
            return "X";
        }

        if (this.round == 2 && this.numberOfDownPin == 10) {
            return "/";
        }

        return String.valueOf(this.numberOfDownPin);
    }

    @Override
    public boolean hasNextRound() {
        if (this.round == MAX_ROUND) {
            return false;
        }

        if (this.numberOfDownPin == 10) {
            return false;
        }

        return true;
    }


}
