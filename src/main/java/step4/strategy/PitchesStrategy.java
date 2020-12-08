package step4.strategy;

@FunctionalInterface
public interface PitchesStrategy {
    int shot(int prevPoint);
}
