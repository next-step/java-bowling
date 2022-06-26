package bowling_step3.state;

public class Done implements State{
    public State pitch(int i) {
        throw new UnsupportedOperationException("cannot pitch more");
    }
}
