package bowling.domain.State;

public class Gutter extends Finished {

    public static State of() {
        return new Gutter();
    }

    @Override
    public String getDisplayText() {
        return GUTTER;
    }
}
