package bowling.domain.state;

public class Gutter implements Finished {

    private Gutter() {
    }

    public static Gutter of() {
        return new Gutter();
    }

    @Override
    public String toString() {
        return "-";
    }
}
