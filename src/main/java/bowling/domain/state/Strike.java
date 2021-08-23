package bowling.domain.state;

public class Strike implements Finished {

    private Strike() {
    }

    public static State of() {
        return new Strike();
    }

    @Override
    public boolean isClear() {
        return true;
    }

    @Override
    public String toString() {
        return "X";
    }
}
