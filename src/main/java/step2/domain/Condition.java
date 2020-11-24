package step2.domain;

@FunctionalInterface
public interface Condition {
    boolean filter(int first, int second);
}
