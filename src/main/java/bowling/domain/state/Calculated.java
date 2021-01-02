package bowling.domain.state;

public interface Calculated extends State{
    @Override
    default boolean isCalculated() {
        return true;
    };
}
