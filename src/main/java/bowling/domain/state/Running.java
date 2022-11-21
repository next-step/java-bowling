package bowling.domain.state;

public abstract class Running extends State {

    public boolean isFinished() {
        return false;
    }
}
