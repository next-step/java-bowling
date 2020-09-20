package bowling.domain.state;

public class Gutter extends FinishedState {

    private Gutter() {
    }

    public static State of() {
        return new Gutter();
    }

}
