package bowling.domain.rolling;

@FunctionalInterface
public interface NormalRollingsOperator {

    boolean invalid(int first, int second);

}
