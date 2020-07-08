package bowling.domain.state;

public class New extends State {

    protected New(int remainPin) {
        super(remainPin);
    }

    @Override
    public boolean isNew() {
        return true;
    }

    @Override
    public boolean isFinish() {
        return false;
    }
}
