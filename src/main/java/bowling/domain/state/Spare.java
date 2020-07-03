package bowling.domain.state;

public class Spare extends State {

    protected Spare(int remainPin) {
        super(remainPin);
    }

    @Override
    public boolean isNew() {
        return false;
    }

    @Override
    public boolean isFinish() {
        return false;
    }
}
