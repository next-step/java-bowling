package bowling.domain.state;

public abstract class Running implements State {

    public boolean isFinished() {
        return false;
    }
}
