package bowling.frame.state;

public abstract class Progress extends State {

    @Override
    public boolean isFinish() {
        return false;
    }

}
