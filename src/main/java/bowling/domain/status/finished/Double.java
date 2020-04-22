package bowling.domain.status.finished;

public class Double extends Finished {

    public static Double of() {
        return new Double();
    }

    private Double() {
        super();
    }

    @Override
    public String print() {
        return "X|X";
    }
}
