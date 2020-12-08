package step3.strategy;

@FunctionalInterface
public interface PitchesStrategy {
    int shot(int prevPoint);
}
