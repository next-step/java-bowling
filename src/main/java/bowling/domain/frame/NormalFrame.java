package bowling.domain.frame;

import bowling.domain.state.FrameBowlStates;

public class NormalFrame implements Frame {

    private final int position;
    private final NormalPins pins;

    private NormalFrame(int position) {
        this.position = position;
        this.pins = new NormalPins();
    }

    static NormalFrame first() {
        return new NormalFrame(0);
    }

    NormalFrame next() {
        return new NormalFrame(this.position + 1);
    }

    @Override
    public void play(int downPin) {
        if (!hasTurn()) {
            throw new IllegalStateException("이미 종료된 frame입니다.");
        }

        this.pins.down(downPin);
    }

    @Override
    public boolean hasTurn() {
        return this.pins.hasTurn();
    }


    @Override
    public FrameBowlStates getBowlStates() {
        return this.pins.getBowlStates();

    }

}
