package bowling.frame.state;

public abstract class Done extends State {

    @Override
    public boolean isFinish() {
        return true;
    }
}
