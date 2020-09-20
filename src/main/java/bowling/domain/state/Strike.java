package bowling.domain.state;

public class Strike extends FinishedState {

    private Strike() {
    }

    public static State of() {
        return new Strike();
    }

}
