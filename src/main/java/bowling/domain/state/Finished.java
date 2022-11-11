package bowling.domain.state;

public abstract class Finished implements State {

    public boolean isFinished() {
        return true;
    }
}
