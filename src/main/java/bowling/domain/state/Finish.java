package bowling.domain.state;

public class Finish extends State {

    protected Finish(int remainPin) {
        super(remainPin);
    }

    @Override
    public boolean isNew() {
        return false;
    }

    @Override
    public boolean isFinish() {
        return true;
    }
}
