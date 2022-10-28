package bowling.domain.state;

public class Strike extends Finished {
    @Override
    public String description() {
        return Symbol.STRIKE;
    }

    @Override
    public int tries() {
        return 1;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Strike;
    }

    @Override
    public int hashCode() {
        return Strike.class.hashCode();
    }
}
