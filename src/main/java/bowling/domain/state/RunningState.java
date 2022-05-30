package bowling.domain.state;

public abstract class RunningState extends State {
    protected RunningState(Pin pin) {
        super(pin);
    }
}
