package bowling.domain.state;

public class Start implements State {

    @Override
    public boolean progressing() {
        return true;
    }

    @Override
    public boolean retryable() {
        return false;
    }

    @Override
    public String toString() {
        return "Start{}";
    }
}
