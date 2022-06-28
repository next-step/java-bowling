package bowling_step3.domain.state;

public class Done implements Status {
    public Status pitch(int i) {
        throw new UnsupportedOperationException("cannot pitch more");
    }
}
