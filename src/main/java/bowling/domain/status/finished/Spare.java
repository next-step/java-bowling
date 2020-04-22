package bowling.domain.status.finished;

public class Spare extends Finished {

    public static Spare of() {
        return new Spare();
    }

    private Spare() {
        super();
    }
}
