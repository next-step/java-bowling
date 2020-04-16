package bowling.domain.State;

public class Strike extends Finished {

    public static State of() {
        return new Strike();
    }

    @Override
    public String getDisplayText() {
        return STRIKE;
    }
}
