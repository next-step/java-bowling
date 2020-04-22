package bowling.domain.status.finished;

public class Gutter extends Finished {

    public static Gutter of() {
        return new Gutter();
    }

    private Gutter() {
        super();
    }

    @Override
    public String print() {
        return "-|-";
    }
}
