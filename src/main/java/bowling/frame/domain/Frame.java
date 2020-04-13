package bowling.frame.domain;

public interface Frame {

    static Frame normal() {
        return NormalFrame.of();
    }

    static Frame last() {
        return LastFrame.of();
    }

    void bowl(int felledPins);

    boolean isFinished();

    String view();

}
