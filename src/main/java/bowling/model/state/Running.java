package bowling.model.state;

public abstract class Running implements State{
    @Override
    public boolean isFinished() {
        return false;
    }
}
