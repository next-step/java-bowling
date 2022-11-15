package bowling.strategy;

@FunctionalInterface
public interface FallenPinCalculateStrategy {
    int calculate(int maxNum);
}
