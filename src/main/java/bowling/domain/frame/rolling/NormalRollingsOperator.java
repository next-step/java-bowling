package bowling.domain.frame.rolling;

@FunctionalInterface
public interface NormalRollingsOperator {

    boolean invalid(int first, int second);

}
