package bowling.model.frame;


import bowling.model.Pin;
import bowling.model.state.Ready;
import bowling.model.state.State;

public class NormalFrame implements Frame {

    public static final int LAST_FRAME_NUMBER = 9;
    public static final int FIRST_FRAME_NUMBER = 1;

    private int number;
    private State state;

   NormalFrame(int number) {
        this.number = number;
        this.state = new Ready();
    }

    public static NormalFrame first() {
        return new NormalFrame(FIRST_FRAME_NUMBER);
    }

    public void bowl(Pin pin) {
        state = state.bowl(pin);
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public Frame nextFrame() {
        if (isLastFrame()){
            return new FinalFrame();
        }
        return new NormalFrame(number + 1);
    }

    private boolean isLastFrame() {
        return number == LAST_FRAME_NUMBER;
    }

    public int getNumber() {
        return number;
    }

    public State getState() {
        return state;
    }
}
