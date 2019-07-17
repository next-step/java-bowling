package domain;

public class NormalFrame {

    private State state;
    private Frame next;

    private NormalFrame() {
        this.state =
    }

    public static NormalFrame of() {
        return new NormalFrame();
    }

}
