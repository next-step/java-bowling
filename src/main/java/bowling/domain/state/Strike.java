package bowling.domain.state;

public class Strike extends BaseState {

    public Strike() {
        super(10);
    }

    @Override
    public String printResult() {
        return BOWLING_STATE_STRIKE;
    }
}
