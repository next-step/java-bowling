package bowling.domain;

public class NormalFrame {
    private State state;
    private NormalFrame next;

    public NormalFrame(){
        state = new State();
    }
    public void bowl(int fallenPins) {
        state.bowl(fallenPins);
    }

    public Pins getFirstPin(){
        return state.getFirstPin();
    }

    public boolean isFinish(){
        if(state.isFinish()){
            next = new NormalFrame();
        }
        return false;
    }

    public NormalFrame next() {
        return next;
    }
}
