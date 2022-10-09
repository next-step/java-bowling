package bowling.step2.domain.state;

public class Strike extends Finished {
    @Override
    public String display() {
        return "X";
    }
}
