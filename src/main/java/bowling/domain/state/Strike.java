package bowling.domain.state;

public class Strike extends EndState {
    private static final String STRIKE_SYMBOL = "X";

    @Override
    public String symbol() {
        return STRIKE_SYMBOL;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }
}