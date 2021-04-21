package bowling.domain.state;

public class Strike extends BaseState {

    @Override
    public String printResult() {
        return BOWLING_STATE_STRIKE;
    }
}
