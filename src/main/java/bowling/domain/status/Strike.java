package bowling.domain.status;

public class Strike extends Finished {
    @Override
    public String printResult() {
        return "X";
    }
}
