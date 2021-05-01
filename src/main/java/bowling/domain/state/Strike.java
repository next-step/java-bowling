package bowling.domain.state;

public final class Strike extends Finish {

    private Strike() {
    }

    public static final State newInstance() {
        return new Strike();
    }

}
