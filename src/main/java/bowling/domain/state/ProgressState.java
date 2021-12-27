package bowling.domain.state;

public abstract class ProgressState implements State{

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}
