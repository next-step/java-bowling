package bowling.domain.status.finished;

public class Strike extends Finished {

    public static Strike of() {
        return new Strike();
    }

    private Strike() {
        super();
    }
}
