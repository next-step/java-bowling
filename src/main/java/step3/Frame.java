package step3;

public class Frame {
    private State state;

    public Frame() {
        this.state = new Ready();
    }

    public void bowl(int fallenPins) {
        state.bowl(fallenPins);
    }
}
