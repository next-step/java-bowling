package bowling.domain.status;

public class Strike implements Status {

    @Override
    public String printResult() {
        return "X";
    }

    @Override
    public String printAllResult() {
        return "X";
    }
}
