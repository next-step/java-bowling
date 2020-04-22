package bowling.domain.status.finished;

public class Miss extends Finished {

    public static Miss of() {
        return new Miss();
    }

    private Miss() {
        super();
    }
}
