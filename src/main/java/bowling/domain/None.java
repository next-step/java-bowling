package bowling.domain;

public class None implements State {
    @Override
    public Score getScore() {
        return null;
    }

    @Override
    public String toString() {
        return "";
    }
}
