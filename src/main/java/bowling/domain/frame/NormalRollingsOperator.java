package bowling.domain.frame;

@FunctionalInterface
public interface NormalRollingsOperator {

    boolean invalid(int first, int second);

}
