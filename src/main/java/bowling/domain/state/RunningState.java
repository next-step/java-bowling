package bowling.domain.state;

public abstract class RunningState extends State {
    public RunningState(Pin pin) {
        super(pin);
    }
}
