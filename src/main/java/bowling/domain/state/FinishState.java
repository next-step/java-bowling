package bowling.domain.state;

public abstract class FinishState implements State {

    @Override
    public boolean isFinish() {
        return true;
    }
}
