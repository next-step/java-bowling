package bowling.domain.frame.domain;

public interface Frame {

    static Frame normal() {
        return NormalFrame.of();
    }

    static Frame last() {
        return LastFrame.of();
    }

    void bowl(int felledPin);

    boolean isFinished();

    String view();

}
