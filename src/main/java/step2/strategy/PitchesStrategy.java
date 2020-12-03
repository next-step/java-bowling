package step2.strategy;

@FunctionalInterface
public interface PitchesStrategy {
    int shot(int prevPoint);
}
