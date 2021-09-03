package bowling.domain;

public class FinalFrame implements Frame {
    private FinalState state;

    public FinalFrame(){
        state = new FinalState();
    }

    @Override
    public void bowl(Pins pins) {
        state.bowl(pins);
    }

    @Override
    public Pins getFirstPin(){
        return state.getFirstPin();
    }

    @Override
    public Pins getSecondPin() {
        return state.getSecondPin();
    }

    @Override
    public boolean isFinish(){
        return state.isFinish();
    }
}
