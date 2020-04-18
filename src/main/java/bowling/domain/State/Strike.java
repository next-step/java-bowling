package bowling.domain.State;

public class Strike extends Finished {
    @Override
    public String getDesc() {
        return STRIKE;
    }
}
