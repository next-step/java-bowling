package bowling.domain;

public class None implements State {
    @Override
    public Score getScore() {
        return new Score();
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
