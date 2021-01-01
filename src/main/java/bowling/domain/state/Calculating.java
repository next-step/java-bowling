package bowling.domain.state;

public interface Calculating extends State {
    @Override
    default boolean isCalculated() {
        return false;
    }
}
